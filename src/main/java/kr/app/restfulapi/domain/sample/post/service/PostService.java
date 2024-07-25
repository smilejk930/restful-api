package kr.app.restfulapi.domain.sample.post.service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.domain.common.user.util.UserPrincipal;
import kr.app.restfulapi.domain.sample.post.dto.PostDto;
import kr.app.restfulapi.domain.sample.post.entity.Post;
import kr.app.restfulapi.domain.sample.post.repository.PostRepository;
import kr.app.restfulapi.global.response.error.exception.ResourceNotFoundException;
import kr.app.restfulapi.global.util.SecurityContextHelper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  @Transactional(readOnly = true)
  public Page<PostDto> getAllPost(PostDto postDto, Pageable pageable) {

    return postRepository.findAllWithCriteria(postDto.toEntity(), pageable).map(PostDto::toDto);
  }

  @Transactional(readOnly = true)
  public Optional<PostDto> getPostById(String postId) {

    /*
    Optional<PostDto> optPostDto;
    if (!SecurityContextHelper.hasAnyRole(RoleGroup.ADMIN_GROUP)) {
      optPostDto = postRepository.findByPostIdAndDeleteAt(postId, "N").map(PostDto::toDto);
    } else {
      UserPrincipal userPrincipal = SecurityContextHelper.getUserPrincipal();
      optPostDto = postRepository.findByPostIdAndDeleteAtAndRegisterId(postId, "N", userPrincipal.getUserId()).map(PostDto::toDto);
    }
    */

    Optional<PostDto> optPostDto = postRepository.findByPostId(postId).map(PostDto::toDto);

    return optPostDto.map(Optional::of).orElseThrow(ResourceNotFoundException::new);
  }

  @Transactional
  public PostDto createPost(PostDto postDto, String sbmsnYn) {

    Post post = postDto.toEntity();
    post.setSbmsnYn(sbmsnYn);
    if ("Y".equals(sbmsnYn)) {
      post.setSbmsnDt(LocalDateTime.now());
    }
    Post savedPost = postRepository.save(post);

    return PostDto.toDto(savedPost);
  }

  @Transactional
  public Optional<PostDto> updatePost(String postId, PostDto postDto, String sbmsnYn) {

    UserPrincipal userPrincipal = SecurityContextHelper.getUserPrincipal();

    Optional<Post> optPost = postRepository.findByPostIdAndDeleteAtAndRegisterId(postId, "N", userPrincipal.getUserId())
        .map(Optional::ofNullable)
        .orElseThrow(ResourceNotFoundException::new);

    Optional<PostDto> optPostDto = optPost.filter(post -> "N".equals(post.getSbmsnYn())).map(post -> {
      post.setSj(postDto.sj());
      post.setCn(postDto.cn());
      post.setSbmsnYn(sbmsnYn);
      if ("Y".equals(sbmsnYn)) {
        post.setSbmsnDt(LocalDateTime.now());
      }

      return PostDto.toDto(post);
    });

    return optPostDto.map(Optional::of).orElseThrow(() -> new ResourceNotFoundException("해당 게시글은 이미 제출되었습니다."));
  }

  @Transactional
  public boolean deletePost(String postId) {

    Optional<Post> optPost = postRepository.findByPostId(postId).map(Optional::ofNullable).orElseThrow(ResourceNotFoundException::new);

    return optPost.filter(post -> "N".equals(post.getSbmsnYn())).map(post -> {
      post.setDeleteAt("Y");

      return true;
    }).orElseThrow(() -> new ResourceNotFoundException("해당 게시글은 이미 제출되었습니다."));
  }
}
