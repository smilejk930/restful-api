package kr.app.restfulapi.domain.common.user.gnrl.dto;

import jakarta.validation.constraints.NotBlank;
import kr.app.restfulapi.domain.common.user.gnrl.entity.GnrlUser;

public record GnrlUserDto(
    @NotBlank(message = "로그인아이디는 필수 입력값입니다.") String lgnId,
    @NotBlank(message = "사용자명은 필수 입력값입니다.") String userNm,
    @NotBlank(message = "비밀번호는 필수 입력값입니다.") String pswd) {

  public static GnrlUserDto toDto(GnrlUser gnrlUser) {
    return new GnrlUserDto(gnrlUser.getLgnId(), gnrlUser.getUserNm(), gnrlUser.getPswd());
  }

  public GnrlUser toEntity() {
    return GnrlUser.builder().lgnId(lgnId).userNm(userNm).pswd(pswd).build();
  }
}
