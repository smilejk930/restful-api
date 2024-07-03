package kr.app.restfulapi.uga.post.service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.uga.post.dto.PostDto;
import kr.app.restfulapi.uga.post.entity.Post;
import kr.app.restfulapi.uga.post.repository.PostRepository;
import kr.app.restfulapi.uga.user.entity.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

  // TODO 게시글 삭제여부 추가

  private final PostRepository postRepository;

  // private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public Page<PostDto> getAllPost(PostDto postDto, Pageable pageable, UserDetails userDetails) {
    /*
     * User user = userRepository.findById(userDetails.getId()).orElseThrow(() ->
     * new RuntimeException("User not found"));
     */
    // TODO USER_ID 추후 변경
    User user = User.builder().userId("1").build();
    return postRepository.findAllWithCriteria(postDto.toEntity(user), pageable).map(PostDto::toDto);
  }

  @Transactional(readOnly = true)
  public Optional<PostDto> getPostById(String postId, UserDetails userDetails) {

    return postRepository.findById(postId).map(PostDto::toDto);

  }

  @Transactional
  public PostDto createPost(PostDto postDto, UserDetails userDetails) {

    // TODO USER_ID 추후 변경
    User user = User.builder().userId("1").build();
    Post post = postDto.toEntity(user);
    Post savedPost = postRepository.save(post);

    return PostDto.toDto(savedPost);
  }

  @Transactional
  public Optional<PostDto> updatePost(String postId, PostDto postDto, UserDetails userDetails) {

    return postRepository.findById(postId).map(post -> {
      post.setSj(postDto.sj());
      post.setCn(postDto.cn());
      post.setUpdusrId("1");// TODO USER_ID 추후 변경
      post.setUpdtDt(LocalDateTime.now());

      return PostDto.toDto(post);
    });
  }

  @Transactional
  public boolean deletePost(String postId, UserDetails userDetails) {

    Optional<Post> optionalPost = postRepository.findById(postId);
    if (optionalPost.isPresent()) {
      postRepository.delete(optionalPost.get());

      return true;
    }

    return false;
  }
}
