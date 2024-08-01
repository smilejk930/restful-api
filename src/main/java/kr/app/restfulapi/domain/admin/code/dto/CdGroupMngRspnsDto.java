package kr.app.restfulapi.domain.admin.code.dto;

import kr.app.restfulapi.domain.common.code.entity.CdGroup;
import kr.app.restfulapi.global.util.CustomDateUtils;

public record CdGroupMngRspnsDto(
    String cdGroupNm,
    String cdKornNm,
    String cdSeNm,
    String cdExpln,
    String useYn,
    String regYmd) {

  public static CdGroupMngRspnsDto toDto(CdGroup cdGroup) {
    return new CdGroupMngRspnsDto(
        cdGroup.getCdGroupNm(),
        cdGroup.getCdKornNm(),
        cdGroup.getCdSeNm(),
        cdGroup.getCdExpln(),
        cdGroup.getUseYn(),
        CustomDateUtils.getFormatYmdDate(cdGroup.getRegDt()));
  }
}
