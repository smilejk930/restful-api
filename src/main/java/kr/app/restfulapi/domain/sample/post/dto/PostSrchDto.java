package kr.app.restfulapi.domain.sample.post.dto;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import kr.app.restfulapi.domain.sample.post.entity.Post;

public record PostSrchDto(
    String sj,
    String cn,
    String userNm,
    String sbmsnYn,
    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate pstgBgngYmd,
    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate pstgEndYmd) {

  public Post toEntity() {
    return Post.builder().srchDto(this).build();
  }
}
