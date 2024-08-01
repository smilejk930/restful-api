package kr.app.restfulapi.domain.common.user.gnrl.dto;

import kr.app.restfulapi.domain.common.user.gnrl.entity.GnrlUser;

public record GnrlUserRspnsDto(
    String lgnId,
    String userNm) {

  public static GnrlUserRspnsDto toDto(GnrlUser gnrlUser) {
    return new GnrlUserRspnsDto(gnrlUser.getLgnId(), gnrlUser.getUserNm());
  }
}
