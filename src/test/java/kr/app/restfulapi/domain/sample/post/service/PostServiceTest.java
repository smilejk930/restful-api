package kr.app.restfulapi.domain.sample.post.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import io.hypersistence.tsid.TSID;
import kr.app.restfulapi.domain.sample.post.dto.PostDto;
import kr.app.restfulapi.domain.sample.post.entity.Post;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest // @SpringBootTest 어노테이션을 사용하여 전체 Spring 컨텍스트를 로드
@Transactional // @Transactional과 @Rollback을 사용하여 테스트 후 데이터베이스 상태를 원래대로 되돌립니다
@Rollback
@Slf4j
public class PostServiceTest {

  @Autowired
  private PostService postService;

  @Test
  void createPost_ShouldGenerateTsid() {

    // Arrange
    // 입력 PostDto 객체를 생성합니다
    PostDto inputDto = PostDto.toDto(Post.builder().sj("입력 제목").cn("입력 내용").build());

    // Act
    // postService.createPost()를 실행하고 결과를 검증합니다.
    PostDto result = postService.createPost(inputDto);

    // Assert
    // 반환된 PostDto의 속성들이 예상한 값과 일치하는지 확인합니다.
    assertNotNull(result.postId());
    assertTrue(TSID.isValid(result.postId())); // TSID 유효성 검사

    // Verify the saved entity
    PostDto savedPostDto = postService.getPostById(result.postId()).orElse(null);
    assertNotNull(savedPostDto);
    log.info("##### postId: {}", savedPostDto.postId());
    assertEquals(result.postId(), savedPostDto.postId());
    assertEquals(result.sj(), savedPostDto.sj());
    assertEquals(result.cn(), savedPostDto.cn());

    // TSID의 시간 정보 검증
    long currentTime = System.currentTimeMillis();
    long tsidTime = TSID.from(result.postId()).getUnixMilliseconds();
    assertTrue(Math.abs(currentTime - tsidTime) < 2000); // 2초 이내의 오차 허용
  }
}
