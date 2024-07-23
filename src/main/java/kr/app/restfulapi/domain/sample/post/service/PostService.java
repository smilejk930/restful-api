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
  public PostDto createPost(PostDto postDto) {

    Post post = postDto.toEntity();
    Post savedPost = postRepository.save(post);

    return PostDto.toDto(savedPost);
  }

  @Transactional
  public Optional<PostDto> updatePost(String postId, PostDto postDto) {

    UserPrincipal userPrincipal = SecurityContextHelper.getUserPrincipal();

    Optional<PostDto> optPostDto = postRepository.findByPostIdAndDeleteAtAndRegisterId(postId, "N", userPrincipal.getUserId()).map(post -> {
      post.setSj(postDto.sj());
      post.setCn(postDto.cn());
      post.setUpdusrId(userPrincipal.getUserId());
      post.setUpdtDt(LocalDateTime.now());

      return PostDto.toDto(post);
    });

    return optPostDto.map(Optional::of).orElseThrow(ResourceNotFoundException::new);
  }

  @Transactional
  public boolean deletePost(String postId) {

    return postRepository.findByPostId(postId).map(post -> {
      UserPrincipal userPrincipal = SecurityContextHelper.getUserPrincipal();
      post.setDeleteAt("Y");
      post.setUpdusrId(userPrincipal.getUserId());
      post.setUpdtDt(LocalDateTime.now());

      return true;
    }).orElseThrow(ResourceNotFoundException::new);
  }
}
