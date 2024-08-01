package kr.app.restfulapi.domain.common.code.dto;

import kr.app.restfulapi.domain.common.code.entity.CdGroup;

public record CdGroupSrchDto(
    String cdGroupNm) {

  public CdGroup toEntity() {
    return CdGroup.builder().cdGroupNm(cdGroupNm).build();
  }
}
