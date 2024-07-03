package kr.app.restfulapi.response.error.exception;

import java.io.IOException;
import org.junit.jupiter.api.Test;
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
