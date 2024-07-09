package kr.app.restfulapi.global.response.error.exception;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import kr.app.restfulapi.global.response.error.exception.BusinessException;
import kr.app.restfulapi.global.response.error.exception.FieldNullPointException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ExceptionTest {

  @Test
  void getBusinessExceptionTest() {
    try {
      throw new IOException("error 발생");
    } catch (IOException e) {
      String message = "error 발생함";
      log.error(message);
      throw new BusinessException(e, message);
    }
  }

  @Test
  void getFieldNullPointException() {
    throw new FieldNullPointException();
  }

  @Test
  void getIllegalArgumentException() {
    throw new IllegalArgumentException();
  }

}
