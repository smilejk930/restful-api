package kr.app.restfulapi.domain.common.code.dto;

import kr.app.restfulapi.domain.common.code.entity.GroupCd;

public record GroupCdSrchDto(
    String cdGroupNm,
    String cdKornNm,
    String useYn) {

  public GroupCd toEntity() {
    return GroupCd.builder().cdGroupNm(cdGroupNm).cdKornNm(cdKornNm).useYn(useYn).build();
  }
}
