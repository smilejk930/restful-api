package kr.app.restfulapi.domain.admin.code.dto;

import kr.app.restfulapi.domain.common.code.entity.Cd;

public record CdMngSrchDto(
    String cdNm,
    String cdKornNm,
    String upCdNm,
    String cdExpln,
    String useYn) {

  public Cd toEntity() {
    return Cd.builder().mngSrchDto(this).build();
  }
}
