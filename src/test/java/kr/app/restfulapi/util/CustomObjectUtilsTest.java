package kr.app.restfulapi.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import kr.app.restfulapi.uga.common.util.CustomObjectUtils;
import kr.app.restfulapi.uga.post.entity.Post;

class CustomObjectUtilsTest {

  @Test
  void isAllFieldNull() {
    Post post = Post.builder().sj("안녕").cn(null).build();
    assertEquals(true, CustomObjectUtils.isAllNullOfFields(post));
  }

}
