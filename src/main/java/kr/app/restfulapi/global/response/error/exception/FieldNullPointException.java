package kr.app.restfulapi.global.response.error.exception;

import kr.app.restfulapi.global.response.error.ErrorStatus;

public class FieldNullPointException extends BusinessException {
  private static final long serialVersionUID = 1L;

  public FieldNullPointException() {
    super(ErrorStatus.BAD_REQUEST);
  }
}
