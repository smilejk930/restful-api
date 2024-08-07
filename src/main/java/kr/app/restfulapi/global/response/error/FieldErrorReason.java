package kr.app.restfulapi.global.response.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FieldErrorReason {

  USER_NOT_FOUND(
      "사용자가 존재하지 않습니다."
  ),
  RESOURCE_NOT_FOUND(
      "정보가 존재하지 않습니다."
  ),
  NO_MATCHING_RESOURCE(
      "일치하는 URL 정보가 존재하지 않습니다."
  ),
  FILE_EXTENSION_NOT_ALLOWED(
      "허용되지 않는 파일 확장자입니다."
  ),
  LOGINID_ALREADY_EXISTS(
      "로그인아이디가 이미 존재합니다."
  ),
  INVALID_PASSWORD(
      "비밀번호가 유효하지 않습니다."
  ),
  BAD_CREDENTIALS(
      "로그인아이디 또는 비밀번호가 유효하지 않습니다."
  );

  /** error가 발생한 항목의 이유 */
  private final String reason;
}
