package kr.app.restfulapi.response.error.exception;

import java.util.List;
import kr.app.restfulapi.response.error.ErrorStatus;
import kr.app.restfulapi.response.error.FieldError;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private ErrorStatus status;
  private List<FieldError> errors;

  public BusinessException(ErrorStatus status) {
    super(status.getMessage());
    this.status = status;
  }

  public BusinessException(ErrorStatus status, List<FieldError> errors) {
    super(status.getMessage());
    this.status = status;
    this.errors = errors;
  }
}
