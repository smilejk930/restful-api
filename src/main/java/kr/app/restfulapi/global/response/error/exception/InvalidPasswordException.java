package kr.app.restfulapi.global.response.error.exception;

import kr.app.restfulapi.global.response.error.ErrorStatus;
import kr.app.restfulapi.global.response.error.FieldError;
import kr.app.restfulapi.global.response.error.FieldErrorReason;

public class InvalidPasswordException extends BusinessException {
  private static final long serialVersionUID = 1L;

  public InvalidPasswordException(String value) {
    super(ErrorStatus.BAD_REQUEST, FieldError.of("pswd", value, FieldErrorReason.INVALID_PASSWORD));
  }
}
