package kr.app.restfulapi.domain.common.user.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kr.app.restfulapi.domain.common.user.gnrl.entity.GnrlUser;

public record LgnReqstDto(
    @NotBlank(message = "로그인아이디는 필수 입력값입니다.") String lgnId,
    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Size(min = 6, max = 50, message = "비밀번호는 {min}자 이상, {max}자 이하로 입력해주세요.") String pswd) {

  public GnrlUser toEntity() {
    return GnrlUser.builder().lgnId(lgnId).pswd(pswd).build();
  }
}
