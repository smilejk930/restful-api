package kr.app.restfulapi.domain.sample.post.service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.global.response.error.exception.ResourceNotFoundException;
import kr.app.restfulapi.domain.sample.post.dto.PostDto;
import kr.app.restfulapi.domain.sample.post.entity.Post;
import kr.app.restfulapi.domain.sample.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

  // TODO 해당하는 USER_ID로 검색

  private final PostRepository postRepository;

  @Transactional(readOnly = true)
  public Page<PostDto> getAllPost(PostDto postDto, Pageable pageable, UserDetails userDetails) {

    // User user = User.builder().userId("1").build();// TODO USER_ID 추후 변경

    return postRepository.findAllWithCriteria(postDto.toEntity(), pageable).map(PostDto::toDto);
  }

  @Transactional(readOnly = true)
  public Optional<PostDto> getPostById(String postId, UserDetails userDetails) {

    Optional<PostDto> optPostDto = postRepository.findByPostIdAndDeleteAt(postId, "N").map(PostDto::toDto);

    return optPostDto.map(Optional::of).orElseThrow(ResourceNotFoundException::new);
  }

  @Transactional
  public PostDto createPost(PostDto postDto, UserDetails userDetails) {

    // User user = User.builder().userId("1").build();// TODO USER_ID 추후 변경
    Post post = postDto.toEntity();
    Post savedPost = postRepository.save(post);

    return PostDto.toDto(savedPost);
  }

  @Transactional
  public Optional<PostDto> updatePost(String postId, PostDto postDto, UserDetails userDetails) {

    Optional<PostDto> optPostDto = postRepository.findByPostIdAndDeleteAt(postId, "N").map(post -> {
      post.setSj(postDto.sj());
      post.setCn(postDto.cn());
      post.setUpdusrId("1");// TODO USER_ID 추후 변경
      post.setUpdtDt(LocalDateTime.now());

      return PostDto.toDto(post);
    });

    return optPostDto.map(Optional::of).orElseThrow(ResourceNotFoundException::new);
  }

  @Transactional
  public boolean deletePost(String postId, UserDetails userDetails) {

    return postRepository.findByPostIdAndDeleteAt(postId, "N").map(post -> {
      post.setDeleteAt("Y");
      post.setUpdusrId("1");// TODO USER_ID 추후 변경
      post.setUpdtDt(LocalDateTime.now());

      return true;
    }).orElseThrow(ResourceNotFoundException::new);
  }
}
