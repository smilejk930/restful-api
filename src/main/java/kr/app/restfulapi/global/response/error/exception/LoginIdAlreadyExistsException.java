package kr.app.restfulapi.global.response.error.exception;

import kr.app.restfulapi.global.response.error.ErrorStatus;
import kr.app.restfulapi.global.response.error.FieldError;
import kr.app.restfulapi.global.response.error.FieldErrorReason;

public class LoginIdAlreadyExistsException extends BusinessException {
  private static final long serialVersionUID = 1L;

  public LoginIdAlreadyExistsException(String value) {
    super(ErrorStatus.BAD_REQUEST, FieldError.of("loginId", value, FieldErrorReason.LOGINID_ALREADY_EXISTS));
  }
}
