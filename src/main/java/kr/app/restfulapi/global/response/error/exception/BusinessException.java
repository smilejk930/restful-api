package kr.app.restfulapi.global.response.error.exception;

import java.util.List;
import kr.app.restfulapi.global.response.error.ErrorStatus;
import kr.app.restfulapi.global.response.error.FieldError;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private ErrorStatus status;
  private List<FieldError> errors;

  public BusinessException(Exception e) {
    super(e);
    this.status = ErrorStatus.INTERNAL_SERVER_ERROR;
  }

  public BusinessException(Exception e, String message) {
    super(e);
    this.status = ErrorStatus.INTERNAL_SERVER_ERROR;
    this.errors = FieldError.of(null, null, message);
  }

  public BusinessException(ErrorStatus status) {
    super(status.getMessage());
    this.status = status;
  }

  public BusinessException(ErrorStatus status, String message) {
    super(status.getMessage());
    this.status = status;
    this.errors = FieldError.of(null, null, message);
  }

  public BusinessException(ErrorStatus status, List<FieldError> errors) {
    super(status.getMessage());
    this.status = status;
    this.errors = errors;
  }
}
