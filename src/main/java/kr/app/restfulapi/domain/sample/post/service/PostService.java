package kr.app.restfulapi.domain.sample.post.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.domain.common.file.dto.FileReqstDto;
import kr.app.restfulapi.domain.common.file.dto.FileRspnsDto;
import kr.app.restfulapi.domain.common.user.gnrl.entity.GnrlUser;
import kr.app.restfulapi.domain.common.user.gnrl.repository.GnrlUserRepository;
import kr.app.restfulapi.domain.common.user.gnrl.util.UserPrincipal;
import kr.app.restfulapi.domain.sample.post.dto.PostReqstDto;
import kr.app.restfulapi.domain.sample.post.dto.PostRspnsDto;
import kr.app.restfulapi.domain.sample.post.dto.PostSrchDto;
import kr.app.restfulapi.domain.sample.post.entity.Post;
import kr.app.restfulapi.domain.sample.post.file.entity.PostFile;
import kr.app.restfulapi.domain.sample.post.file.service.PostFileService;
import kr.app.restfulapi.domain.sample.post.repository.PostRepository;
import kr.app.restfulapi.global.response.error.exception.ResourceNotFoundException;
import kr.app.restfulapi.global.util.SecurityContextHelper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final GnrlUserRepository gnrlUserRepository;
  private final PostFileService fileService;

  @Transactional(readOnly = true)
  public Page<PostRspnsDto> getAllPost(PostSrchDto srchDto, Pageable pageable) {

    return postRepository.findAllWithCriteria(srchDto.toEntity(), pageable).map(post -> PostRspnsDto.toDto(post, null));
  }

  @Transactional(readOnly = true)
  public Optional<PostRspnsDto> getPostById(String postTsid) {

    /*
    Optional<PostDto> optPostDto;
    if (!SecurityContextHelper.hasAnyRole(UserGroup.ADMIN_GROUP)) {
      optPostDto = postRepository.findByPostTsidAndDelYn(postTsid, "N").map(PostDto::toDto);
    } else {
      UserPrincipal userPrincipal = SecurityContextHelper.getUserPrincipal();
      optPostDto = postRepository.findByPostTsidAndDelYnAndRgtrTsid(postTsid, "N", userPrincipal.getUserTsid()).map(PostDto::toDto);
    }
    */

    Optional<PostRspnsDto> optPostRspnsDto = postRepository.findByPostTsid(postTsid).map(post -> PostRspnsDto.toDto(post, null));

    return optPostRspnsDto.map(Optional::of).orElseThrow(ResourceNotFoundException::new);
  }

  @Transactional
  public PostRspnsDto createPost(PostReqstDto postReqstDto, String sbmsnYn) {

    Post post = postReqstDto.toEntity();
    post.setSbmsnYn(sbmsnYn);
    if ("Y".equals(sbmsnYn)) {
      post.setSbmsnDt(LocalDateTime.now());
    }
    Post savedPost = postRepository.save(post);

    UserPrincipal userPrincipal = SecurityContextHelper.getUserPrincipal();
    savedPost.setRgtrNm(userPrincipal.getUserNm());

    /** 파일 업로드 */
    List<FileReqstDto<PostFile>> fileReqstDtos = postReqstDto.getFileReqstDtos();
    List<FileRspnsDto> uploadedFiles = new ArrayList<>();

    fileReqstDtos.forEach(fileReqstDto -> {
      fileReqstDto.setRfrncTsid(savedPost.getPostTsid());

      List<FileRspnsDto> currentUploadedFiles = fileService.storeFiles(fileReqstDto.getFiles(), fileReqstDto);
      uploadedFiles.addAll(currentUploadedFiles);
    });

    return PostRspnsDto.toDto(savedPost, uploadedFiles);
  }

  @Transactional
  public Optional<PostRspnsDto> updatePost(String postTsid, PostReqstDto postReqstDto, String sbmsnYn) {

    UserPrincipal userPrincipal = SecurityContextHelper.getUserPrincipal();

    Optional<Post> optPost = postRepository.findByPostTsidAndDelYnAndRgtrTsid(postTsid, "N", userPrincipal.getUserTsid())
        .map(Optional::ofNullable)
        .orElseThrow(ResourceNotFoundException::new);

    Optional<PostRspnsDto> optPostRspnsDto = optPost.filter(post -> "N".equals(post.getSbmsnYn())).map(post -> {
      post.setTtl(postReqstDto.getTtl());
      post.setCn(postReqstDto.getCn());
      post.setSbmsnYn(sbmsnYn);
      if ("Y".equals(sbmsnYn)) {
        post.setSbmsnDt(LocalDateTime.now());
      }

      GnrlUser optRgtrUser = gnrlUserRepository.findByUserTsid(post.getRgtrTsid()).orElse(GnrlUser.builder().build());
      post.setRgtrNm(optRgtrUser.getUserNm());

      return PostRspnsDto.toDto(post, null);
    });

    return optPostRspnsDto.map(Optional::of).orElseThrow(() -> new ResourceNotFoundException("해당 게시글은 이미 제출되었습니다."));
  }

  @Transactional
  public boolean deletePost(String postTsid) {

    Optional<Post> optPost = postRepository.findByPostTsid(postTsid).map(Optional::ofNullable).orElseThrow(ResourceNotFoundException::new);

    return optPost.filter(post -> "N".equals(post.getSbmsnYn())).map(post -> {
      post.setDelYn("Y");

      return true;
    }).orElseThrow(() -> new ResourceNotFoundException("해당 게시글은 이미 제출되었습니다."));
  }
}
