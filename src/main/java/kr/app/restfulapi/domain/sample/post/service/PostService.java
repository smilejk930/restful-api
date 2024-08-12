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
import kr.app.restfulapi.domain.common.file.util.FileGroupNmType;
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

    Optional<PostRspnsDto> optPostRspnsDto = postRepository.findByPostTsid(postTsid).map(post -> {

      /** 파일 조회 - 시작 */
      List<FileRspnsDto> fileRspnsDtos =
          fileService.getAllFiles(FileReqstDto.<PostFile>builder().fileGroupNm(FileGroupNmType.SAMPLE_POST).rfrncTsid(postTsid).build());
      /** 파일 조회 - 끝 */
      return PostRspnsDto.toDto(post, fileRspnsDtos);
    });

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

    /** 파일 업로드 - 시작 */
    List<FileReqstDto<PostFile>> fileReqstDtos = postReqstDto.getFileReqstDtos();
    List<FileRspnsDto> uploadedFiles = new ArrayList<>();

    fileReqstDtos.stream().filter(fileReqstDto -> fileReqstDto.getFiles() != null && !fileReqstDto.getFiles().isEmpty()).forEach(fileReqstDto -> {
      fileReqstDto.setFileGroupNm(FileGroupNmType.SAMPLE_POST);
      fileReqstDto.setRfrncTsid(savedPost.getPostTsid());

      List<FileRspnsDto> currentUploadedFiles = fileService.storeFiles(fileReqstDto.getFiles(), fileReqstDto);
      uploadedFiles.addAll(currentUploadedFiles);
    });
    /** 파일 업로드 - 종료 */

    return PostRspnsDto.toDto(savedPost, uploadedFiles);
  }

  @Transactional
  public Optional<PostRspnsDto> updatePost(String postTsid, PostReqstDto postReqstDto, String sbmsnYn) {

    UserPrincipal userPrincipal = SecurityContextHelper.getUserPrincipal();

    Optional<Post> optPost = postRepository.findByPostTsidAndDelYnAndRgtrTsid(postTsid, "N", userPrincipal.getUserTsid())
        .map(Optional::of)
        .orElseThrow(ResourceNotFoundException::new);

    Optional<PostRspnsDto> optPostRspnsDto = optPost.filter(post -> "N".equals(post.getSbmsnYn())).map(post -> {
      post.setTtl(postReqstDto.getTtl());
      post.setCn(postReqstDto.getCn());
      post.setTelgmLen(postReqstDto.getTelgmLen());
      post.setSbmsnYn(sbmsnYn);
      if ("Y".equals(sbmsnYn)) {
        post.setSbmsnDt(LocalDateTime.now());
      }

      GnrlUser optRgtrUser = gnrlUserRepository.findByUserTsid(post.getRgtrTsid()).orElse(GnrlUser.builder().build());
      post.setRgtrNm(optRgtrUser.getUserNm());

      /** 파일 삭제/업로드 - 시작 */
      // 파일 파라미터 객체
      List<FileReqstDto<PostFile>> fileReqstDtos = postReqstDto.getFileReqstDtos();

      // 파일 조회
      List<FileRspnsDto> fileRspnsDtos =
          fileService.getAllFiles(FileReqstDto.<PostFile>builder().fileGroupNm(FileGroupNmType.SAMPLE_POST).rfrncTsid(postTsid).build());

      List<String> fileTsids = fileRspnsDtos.stream().map(FileRspnsDto::fileTsid).toList();

      // 파일 삭제
      fileReqstDtos.stream()
          .filter(fileReqstDto -> fileReqstDto.getDelFileTsids() != null && !fileReqstDto.getDelFileTsids().isEmpty())
          .forEach(fileReqstDto -> fileService.deleteFiles(fileTsids, fileReqstDto.getDelFileTsids()));

      // 파일 업로드
      fileReqstDtos.stream().filter(fileReqstDto -> fileReqstDto.getFiles() != null && !fileReqstDto.getFiles().isEmpty()).forEach(fileReqstDto -> {
        fileReqstDto.setFileGroupNm(FileGroupNmType.SAMPLE_POST);
        fileReqstDto.setRfrncTsid(post.getPostTsid());
        fileService.storeFiles(fileReqstDto.getFiles(), fileReqstDto);
      });

      // 수정된 파일 조회
      List<FileRspnsDto> newFileRspnsDtos =
          fileService.getAllFiles(FileReqstDto.<PostFile>builder().fileGroupNm(FileGroupNmType.SAMPLE_POST).rfrncTsid(postTsid).build());
      /** 파일 삭제/업로드 - 종료 */

      return PostRspnsDto.toDto(post, newFileRspnsDtos);
    });

    return optPostRspnsDto.map(Optional::of).orElseThrow(() -> new ResourceNotFoundException("해당 게시글은 이미 제출되었습니다."));
  }

  @Transactional
  public boolean deletePost(String postTsid) {

    Optional<Post> optPost = postRepository.findByPostTsid(postTsid).map(Optional::ofNullable).orElseThrow(ResourceNotFoundException::new);

    return optPost.filter(post -> "N".equals(post.getSbmsnYn())).map(post -> {
      post.setDelYn("Y");
      // TODO 게시글 삭제 시 파일들도 삭제
      return true;
    }).orElseThrow(() -> new ResourceNotFoundException("해당 게시글은 이미 제출되었습니다."));
  }
}
