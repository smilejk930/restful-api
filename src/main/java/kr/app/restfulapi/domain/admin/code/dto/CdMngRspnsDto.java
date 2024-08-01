package kr.app.restfulapi.domain.admin.code.dto;

import kr.app.restfulapi.domain.common.code.entity.Cd;
import kr.app.restfulapi.global.util.CustomDateUtils;

public record CdMngRspnsDto(
    String cdGroupNm,
    String cdNm,
    String cdKornNm,
    String upCdNm,
    Integer cdSeq,
    String cdExpln,
    String useYn,
    String regYmd) {

  public static CdMngRspnsDto toDto(Cd cd) {
    return new CdMngRspnsDto(
        cd.getCdGroupNm(),
        cd.getCdNm(),
        cd.getCdKornNm(),
        cd.getUpCdNm(),
        cd.getCdSeq(),
        cd.getCdExpln(),
        cd.getUseYn(),
        CustomDateUtils.getFormatYmdDate(cd.getRegDt()));
  }
}
