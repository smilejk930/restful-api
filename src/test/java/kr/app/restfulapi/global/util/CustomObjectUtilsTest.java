package kr.app.restfulapi.global.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import kr.app.restfulapi.domain.sample.post.entity.Post;

class CustomObjectUtilsTest {

  @Test
  void isAllFieldNull() {
    Post post = Post.builder().ttl("안녕").cn(null).build();
    assertEquals(true, CustomObjectUtils.isAllNullOfFields(post));
  }

}
