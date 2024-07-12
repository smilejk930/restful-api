package kr.app.restfulapi.global.response.error.exception;

import java.util.List;
import kr.app.restfulapi.global.response.error.ErrorStatus;
import kr.app.restfulapi.global.response.error.FieldError;
import kr.app.restfulapi.global.response.error.FieldErrorReason;

public class IllegalArgumentException extends BusinessException {
  private static final long serialVersionUID = 1L;
  private static final ErrorStatus errorStatus = ErrorStatus.BAD_REQUEST;

  public IllegalArgumentException() {
    super(errorStatus);
  }

  public IllegalArgumentException(String message) {
    super(errorStatus, message);
  }

  public IllegalArgumentException(String field, String value, String reason) {
    super(errorStatus, FieldError.of(field, value, reason));
  }

  public IllegalArgumentException(String field, String value, FieldErrorReason reason) {
    super(errorStatus, FieldError.of(field, value, reason));
  }

  public IllegalArgumentException(List<FieldError> errors) {
    super(errorStatus, errors);
  }
}
