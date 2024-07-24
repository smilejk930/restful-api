package kr.app.restfulapi.domain.sample.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kr.app.restfulapi.domain.sample.post.entity.Post;
import kr.app.restfulapi.global.util.CustomDateUtils;

public record PostDto(
    String postId,
    @NotBlank(message = "제목은 필수 입력값입니다.") @Size(min = 3, max = 200, message = "제목은 {min}자 이상, {max}자 이하로 입력해주세요.") String sj,
    @NotBlank(message = "내용은 필수 입력값입니다.") @Size(min = 1, max = 4000, message = "내용은 {min}자 이상, {max}자 이하로 입력해주세요.") String cn,
    String userNm,
    String registYmd) {

  public static PostDto toDto(Post post) {
    return new PostDto(post.getPostId(), post.getSj(), post.getCn(), post.getUserNm(), CustomDateUtils.getFormatYmdDate(post.getRegistDt()));
  }

  public Post toEntity() {
    return Post.builder().postId(postId).sj(sj).cn(cn).build();
  }
}
