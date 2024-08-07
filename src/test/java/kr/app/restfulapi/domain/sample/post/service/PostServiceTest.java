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
import kr.app.restfulapi.domain.sample.post.dto.PostReqstDto;
import kr.app.restfulapi.domain.sample.post.dto.PostRspnsDto;
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
    // PostReqstDto inputDto = PostReqstDto.toDto(Post.builder().ttl("입력 제목").cn("입력 내용").build());
    PostReqstDto inputDto = null;// PostReqstDto 생성자 만들수 없음, 테스트 에러 발생함

    // Act
    // postService.createPost()를 실행하고 결과를 검증합니다.
    PostRspnsDto result = postService.createPost(inputDto, "N");

    // Assert
    // 반환된 PostDto의 속성들이 예상한 값과 일치하는지 확인합니다.
    assertNotNull(result.postTsid());
    assertTrue(TSID.isValid(result.postTsid())); // TSID 유효성 검사

    // Verify the saved entity
    PostRspnsDto savedPostDto = postService.getPostById(result.postTsid()).orElse(null);
    assertNotNull(savedPostDto);
    log.info("##### postTsid: {}", savedPostDto.postTsid());
    assertEquals(result.postTsid(), savedPostDto.postTsid());
    assertEquals(result.ttl(), savedPostDto.ttl());
    assertEquals(result.cn(), savedPostDto.cn());

    // TSID의 시간 정보 검증
    long currentTime = System.currentTimeMillis();
    long tsidTime = TSID.from(result.postTsid()).getUnixMilliseconds();
    assertTrue(Math.abs(currentTime - tsidTime) < 2000); // 2초 이내의 오차 허용
  }
}
