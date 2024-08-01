package kr.app.restfulapi.domain.admin.code.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import kr.app.restfulapi.domain.common.code.entity.Cd;

public record CdMngReqstDto(
    @NotBlank(message = "코드한글명은 필수 입력값입니다.")
    @Size(min = 1, max = 50, message = "코드한글명은 {min}자 이상, {max}자 이하로 입력해주세요.") String cdKornNm,

    @Size(min = 1, max = 50, message = "상위코드명은 {min}자 이상, {max}자 이하로 입력해주세요.") String upCdNm,

    @NotNull(message = "코드순서는 필수 입력값입니다.")
    @Positive(message = "코드순서는 양수여야 합니다.") Integer cdSeq,

    String cdExpln,

    @Size(max = 1, message = "사용여부는 {max}자 입니다.")
    @Pattern(regexp = "[YN]", message = "사용여부는 Y 또는 N 이어야 합니다.") String useYn) {

  public Cd toEntity() {
    return Cd.builder().cdKornNm(cdKornNm).upCdNm(upCdNm).cdSeq(cdSeq).cdExpln(cdExpln).useYn(useYn).build();
  }
}
