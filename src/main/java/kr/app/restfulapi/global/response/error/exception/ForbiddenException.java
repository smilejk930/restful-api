package kr.app.restfulapi.global.response.error.exception;

import java.util.List;
import kr.app.restfulapi.global.response.error.ErrorStatus;
import kr.app.restfulapi.global.response.error.FieldError;
import kr.app.restfulapi.global.response.error.FieldErrorReason;

public class ForbiddenException extends BusinessException {
  private static final long serialVersionUID = 1L;
  private static final ErrorStatus errorStatus = ErrorStatus.FORBIDDEN;

  public ForbiddenException() {
    super(errorStatus);
  }

  public ForbiddenException(String message) {
    super(errorStatus, message);
  }

  public ForbiddenException(String field, String value, String reason) {
    super(errorStatus, FieldError.of(field, value, reason));
  }

  public ForbiddenException(String field, String value, FieldErrorReason reason) {
    super(errorStatus, FieldError.of(field, value, reason));
  }

  public ForbiddenException(List<FieldError> errors) {
    super(errorStatus, errors);
  }
}
