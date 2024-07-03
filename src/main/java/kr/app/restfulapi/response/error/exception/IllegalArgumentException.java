package kr.app.restfulapi.response.error.exception;

import java.util.List;
import kr.app.restfulapi.response.error.ErrorStatus;
import kr.app.restfulapi.response.error.FieldError;
import kr.app.restfulapi.response.error.FieldErrorReason;

public class IllegalArgumentException extends BusinessException {
  private static final long serialVersionUID = 1L;

  public IllegalArgumentException() {
    super(ErrorStatus.BAD_REQUEST);
  }

  public IllegalArgumentException(String message) {
    super(ErrorStatus.BAD_REQUEST, message);
  }

  public IllegalArgumentException(String field, String value, String reason) {
    super(ErrorStatus.BAD_REQUEST, FieldError.of(field, value, reason));
  }

  public IllegalArgumentException(String field, String value, FieldErrorReason reason) {
    super(ErrorStatus.BAD_REQUEST, FieldError.of(field, value, reason));
  }

  public IllegalArgumentException(List<FieldError> errors) {
    super(ErrorStatus.BAD_REQUEST, errors);
  }
}
