package kr.app.restfulapi.domain.sample.post.dto;

import java.util.List;
import java.util.Objects;
import kr.app.restfulapi.domain.common.file.dto.FileRspnsDto;
import kr.app.restfulapi.domain.sample.post.entity.Post;
import kr.app.restfulapi.global.util.CustomDateUtils;

public record PostRspnsDto(
    String postTsid,
    String ttl,
    String cn,
    Integer telgmLen,
    String sbmsnYn,
    String rgtrNm,
    String regYmd,
    List<FileRspnsDto> files) {

  public static PostRspnsDto toDto(Post post, List<FileRspnsDto> files) {
    return new PostRspnsDto(
        post.getPostTsid(),
        post.getTtl(),
        post.getCn(),
        post.getTelgmLen(),
        post.getSbmsnYn(),
        Objects.toString(post.getRgtrNm(), "-"),
        CustomDateUtils.getFormatYmdDate(post.getRegDt()),
        files);
  }
}
