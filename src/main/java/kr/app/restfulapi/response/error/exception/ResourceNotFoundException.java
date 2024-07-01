package kr.app.restfulapi.response.error.exception;

import kr.app.restfulapi.response.error.ErrorStatus;

public class ResourceNotFoundException extends BusinessException {
  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException() {
    super(ErrorStatus.NOT_FOUND);
  }
  /*
  public ResourceNotFoundException(String field, String value) {
    super(ErrorStatus.NOT_FOUND, FieldError.of(field, value, FieldErrorReason.RESOURCE_NOT_FOUND));
  }*/
}
