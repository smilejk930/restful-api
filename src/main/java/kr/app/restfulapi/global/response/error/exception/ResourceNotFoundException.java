package kr.app.restfulapi.global.response.error.exception;

import kr.app.restfulapi.global.response.error.ErrorStatus;

public class ResourceNotFoundException extends BusinessException {
  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException() {
    super(ErrorStatus.NOT_FOUND);
  }
}
