package kr.app.restfulapi.global.response.error.exception;

import java.util.List;
import kr.app.restfulapi.global.response.error.ErrorStatus;
import kr.app.restfulapi.global.response.error.FieldError;
import kr.app.restfulapi.global.response.error.FieldErrorReason;

public class UnauthorizedException extends BusinessException {
  private static final long serialVersionUID = 1L;
  private static final ErrorStatus errorStatus = ErrorStatus.UNAUTHORIZED;

  public UnauthorizedException() {
    super(errorStatus);
  }

  public UnauthorizedException(String message) {
    super(errorStatus, message);
  }

  public UnauthorizedException(String field, String value, String reason) {
    super(errorStatus, FieldError.of(field, value, reason));
  }

  public UnauthorizedException(String field, String value, FieldErrorReason reason) {
    super(errorStatus, FieldError.of(field, value, reason));
  }

  public UnauthorizedException(List<FieldError> errors) {
    super(errorStatus, errors);
  }
}
