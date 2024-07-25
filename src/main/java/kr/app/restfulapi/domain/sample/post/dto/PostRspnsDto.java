package kr.app.restfulapi.domain.sample.post.dto;

import java.util.Objects;
import kr.app.restfulapi.domain.sample.post.entity.Post;
import kr.app.restfulapi.global.util.CustomDateUtils;

public record PostRspnsDto(
    String postId,
    String sj,
    String cn,
    Integer telgmLen,
    String userNm,
    String sbmsnYn,
    String registYmd) {

  public static PostRspnsDto toDto(Post post) {
    return new PostRspnsDto(
        post.getPostId(),
        post.getSj(),
        post.getCn(),
        post.getTelgmLen(),
        Objects.toString(post.getUserNm(), "-"),
        post.getSbmsnYn(),
        CustomDateUtils.getFormatYmdDate(post.getRegistDt()));
  }
}
