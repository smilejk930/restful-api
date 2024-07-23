package kr.app.restfulapi.domain.sample.post.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import io.hypersistence.tsid.TSID;
import kr.app.restfulapi.domain.sample.post.dto.PostDto;
import kr.app.restfulapi.domain.sample.post.entity.Post;
import kr.app.restfulapi.domain.sample.post.repository.PostRepository;

@ExtendWith(MockitoExtension.class) // @ExtendWith(MockitoExtension.class)를 사용하여 Mockito를 JUnit 5와 통합합니다.
public class PostServiceNoneDBTest {

  @Mock
  private PostRepository postRepository; // @Mock 어노테이션으로 PostRepository를 모킹합니다.

  @InjectMocks
  private PostService postService; // @InjectMocks를 사용하여 테스트 대상인 PostService에 모킹된 의존성을 주입합니다.

  @Test
  void createPost_ShouldReturnPostDto() {

    // Arrange
    // 입력 PostDto 객체를 생성합니다
    PostDto inputDto = PostDto.toDto(Post.builder().sj("입력 제목").cn("입력 내용").build());

    // postRepository.save() 메소드가 호출될 때 savedPost를 반환하도록 설정
    when(postRepository.save(any(Post.class))).thenAnswer(invocation -> {
      Post post = invocation.getArgument(0);
      post.setPostId(TSID.fast().toString()); // TSID 생성 시뮬레이션
      post.setSj("입력 제목");
      post.setCn("입력 내용");
      return post;
    });
    // when(postRepository.save(any(Post.class))).thenReturn(savedPost);

    // Act
    // postService.createPost()를 실행하고 결과를 검증합니다.
    /* postRepository.save(post)가 호출되면,
     * 실제 데이터베이스에 접근하지 않고 미리 설정한 savedPost 객체를 반환하도록 합니다.
     * 이를 통해 데이터베이스와의 의존성을 제거하고, 메소드의 로직만을 테스트할 수 있게 됩니다.
     */
    PostDto result = postService.createPost(inputDto);

    // Assert
    // 반환된 PostDto의 속성들이 예상한 값과 일치하는지 확인합니다.
    assertNotNull(result.postId());
    assertTrue(TSID.isValid(result.postId())); // TSID 유효성 검사
    assertEquals(inputDto.sj(), result.sj());
    assertEquals(inputDto.cn(), result.cn());

    // TSID의 시간 정보 검증
    long currentTime = System.currentTimeMillis();
    long tsidTime = TSID.from(result.postId()).getUnixMilliseconds();
    assertTrue(Math.abs(currentTime - tsidTime) < 1000); // 1초 이내의 오차 허용

    // postRepository.save() 메소드가 정확히 한 번 호출되었는지 검증합니다.
    verify(postRepository, times(1)).save(any(Post.class));
  }

}
