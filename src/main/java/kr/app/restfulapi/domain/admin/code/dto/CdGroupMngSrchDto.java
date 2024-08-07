package kr.app.restfulapi.domain.admin.code.dto;

import kr.app.restfulapi.domain.common.code.entity.CdGroup;

public record CdGroupMngSrchDto(
    String cdGroupNm,
    String cdKornNm,
    String cdSeNm,
    String cdExpln,
    String useYn,
    String comCdYn) {

  public CdGroup toEntity() {
    return CdGroup.builder().mngSrchDto(this).build();
  }
}
