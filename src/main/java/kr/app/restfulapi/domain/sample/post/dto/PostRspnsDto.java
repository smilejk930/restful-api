package kr.app.restfulapi.domain.sample.post.dto;

import java.util.Objects;
import kr.app.restfulapi.domain.sample.post.entity.Post;
import kr.app.restfulapi.global.util.CustomDateUtils;

public record PostRspnsDto(
    String postTsid,
    String ttl,
    String cn,
    Integer telgmLen,
    String userNm,
    String sbmsnYn,
    String regYmd) {

  public static PostRspnsDto toDto(Post post) {
    return new PostRspnsDto(
        post.getPostTsid(),
        post.getTtl(),
        post.getCn(),
        post.getTelgmLen(),
        Objects.toString(post.getUserNm(), "-"),
        post.getSbmsnYn(),
        CustomDateUtils.getFormatYmdDate(post.getRegDt()));
  }
}
