package kr.app.restfulapi.global.response.error.exception;

import java.util.List;
import kr.app.restfulapi.global.response.error.ErrorStatus;
import kr.app.restfulapi.global.response.error.FieldError;
import kr.app.restfulapi.global.response.error.FieldErrorReason;

public class JwtTokenException extends BusinessException {
  private static final long serialVersionUID = 1L;
  private static final ErrorStatus errorStatus = ErrorStatus.FORBIDDEN;

  public JwtTokenException() {
    super(errorStatus);
  }

  public JwtTokenException(String message) {
    super(errorStatus, message);
  }

  public JwtTokenException(String field, String value, String reason) {
    super(errorStatus, FieldError.of(field, value, reason));
  }

  public JwtTokenException(String field, String value, FieldErrorReason reason) {
    super(errorStatus, FieldError.of(field, value, reason));
  }

  public JwtTokenException(List<FieldError> errors) {
    super(errorStatus, errors);
  }
}
