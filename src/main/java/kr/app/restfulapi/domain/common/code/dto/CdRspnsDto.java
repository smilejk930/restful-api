package kr.app.restfulapi.domain.common.code.dto;

import kr.app.restfulapi.domain.common.code.entity.Cd;

public record CdRspnsDto(
    String cdGroupNm,
    String cdNm,
    String cdKornNm,
    String upCdNm,
    Integer cdSeq,
    String cdExpln) {

  public static CdRspnsDto toDto(Cd cd) {
    return new CdRspnsDto(cd.getCdGroup().getCdGroupNm(), cd.getCdNm(), cd.getCdKornNm(), cd.getUpCdNm(), cd.getCdSeq(), cd.getCdExpln());
  }
}
