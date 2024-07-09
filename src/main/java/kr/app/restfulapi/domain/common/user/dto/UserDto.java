package kr.app.restfulapi.domain.common.user.dto;

import jakarta.validation.constraints.NotBlank;
import kr.app.restfulapi.domain.common.user.entity.User;

public record UserDto(
    @NotBlank(message = "로그인아이디는 필수 입력값입니다.") String loginId,
    @NotBlank(message = "사용자명은 필수 입력값입니다.") String userNm,
    @NotBlank(message = "비밀번호는 필수 입력값입니다.") String password) {

  public static UserDto toDto(User user) {
    return new UserDto(user.getLoginId(), user.getUserNm(), user.getPassword());
  }

  public User toEntity() {
    return User.builder().loginId(loginId).userNm(userNm).password(password).build();
  }
}
