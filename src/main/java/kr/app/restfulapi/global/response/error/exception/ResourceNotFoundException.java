package kr.app.restfulapi.global.response.error.exception;

import java.util.List;
import kr.app.restfulapi.global.response.error.ErrorStatus;
import kr.app.restfulapi.global.response.error.FieldError;
import kr.app.restfulapi.global.response.error.FieldErrorReason;

public class ResourceNotFoundException extends BusinessException {
  private static final long serialVersionUID = 1L;
  private static final ErrorStatus errorStatus = ErrorStatus.NOT_FOUND;

  public ResourceNotFoundException() {
    super(errorStatus);
  }

  public ResourceNotFoundException(String message) {
    super(errorStatus, message);
  }

  public ResourceNotFoundException(String field, String value, String reason) {
    super(errorStatus, FieldError.of(field, value, reason));
  }

  public ResourceNotFoundException(String field, String value, FieldErrorReason reason) {
    super(errorStatus, FieldError.of(field, value, reason));
  }

  public ResourceNotFoundException(List<FieldError> errors) {
    super(errorStatus, errors);
  }
}
