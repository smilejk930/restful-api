package kr.app.restfulapi.global.response.error.exception;

import java.util.List;
import kr.app.restfulapi.global.response.error.ErrorStatus;
import kr.app.restfulapi.global.response.error.FieldError;
import kr.app.restfulapi.global.response.error.FieldErrorReason;

public class JwtAuthenticationException extends BusinessException {
  private static final long serialVersionUID = 1L;
  private static final ErrorStatus errorStatus = ErrorStatus.UNAUTHORIZED;

  public JwtAuthenticationException() {
    super(errorStatus);
  }

  public JwtAuthenticationException(String message) {
    super(errorStatus, message);
  }

  public JwtAuthenticationException(String field, String value, String reason) {
    super(errorStatus, FieldError.of(field, value, reason));
  }

  public JwtAuthenticationException(String field, String value, FieldErrorReason reason) {
    super(errorStatus, FieldError.of(field, value, reason));
  }

  public JwtAuthenticationException(List<FieldError> errors) {
    super(errorStatus, errors);
  }
}
