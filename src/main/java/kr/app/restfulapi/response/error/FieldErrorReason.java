package kr.app.restfulapi.response.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FieldErrorReason {

  USER_NOT_FOUND(
      "사용자가 존재하지 않습니다."
  );

  /** error가 발생한 항목의 이유 */
  private final String reason;
}
