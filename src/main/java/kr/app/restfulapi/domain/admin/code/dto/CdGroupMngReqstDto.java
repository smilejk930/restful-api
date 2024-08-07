package kr.app.restfulapi.domain.admin.code.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kr.app.restfulapi.domain.common.code.entity.CdGroup;
import kr.app.restfulapi.global.validation.ValidationGroups.Create;
import kr.app.restfulapi.global.validation.ValidationGroups.Update;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CdGroupMngReqstDto {

  @NotBlank(message = "코드그룹명은 필수 입력값입니다.", groups = {Create.class})
  @Size(min = 3, max = 100, message = "코드그룹명은 {min}자 이상, {max}자 이하로 입력해주세요.", groups = {Create.class})
  private String cdGroupNm;

  @NotBlank(message = "코드한글명은 필수 입력값입니다.", groups = {Create.class, Update.class})
  @Size(min = 1, max = 50, message = "코드한글명은 {min}자 이상, {max}자 이하로 입력해주세요.", groups = {Create.class, Update.class})
  private String cdKornNm;

  @NotBlank(message = "코드구분명은 필수 입력값입니다.", groups = {Create.class})
  @Size(min = 3, max = 3, message = "코드구분명은 정확히 {max}자 입니다.", groups = {Create.class})
  private String cdSeNm;

  private String cdExpln;

  @NotBlank(message = "사용여부는 필수 입력값입니다.")
  @Size(max = 1, message = "사용여부는 {max}자 입니다.", groups = {Create.class, Update.class})
  @Pattern(regexp = "[YN]", message = "사용여부는 Y 또는 N 이어야 합니다.", groups = {Create.class, Update.class})
  private String useYn;

  @NotBlank(message = "공통코드여부는 필수 입력값입니다.")
  @Size(max = 1, message = "공통코드여부는 {max}자 입니다.", groups = {Create.class, Update.class})
  @Pattern(regexp = "[YN]", message = "공통코드여부는 Y 또는 N 이어야 합니다.", groups = {Create.class, Update.class})
  private String comCdYn;

  public CdGroup toEntity() {
    return CdGroup.builder()
        .cdGroupNm(cdGroupNm)
        .cdKornNm(cdKornNm)
        .cdSeNm(cdSeNm.toUpperCase())
        .cdExpln(cdExpln)
        .useYn(useYn)
        .comCdYn(comCdYn)
        .build();
  }
}
