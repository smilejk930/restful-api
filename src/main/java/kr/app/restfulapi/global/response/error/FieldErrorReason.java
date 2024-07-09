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
  FILE_EXTENSION_NOT_ALLOWED(
      "허용되지 않는 파일 확장자입니다."
  );

  /** error가 발생한 항목의 이유 */
  private final String reason;
}
