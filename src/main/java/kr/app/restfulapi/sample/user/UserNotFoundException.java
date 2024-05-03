package kr.app.restfulapi.sample.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * 예외처리 구현
 * RuntimeException으로 500을 반환하지 않고,
 * HttpStatus.NOT_FOUND를 설정하여 사용자가 존재하지 않을 경우 404 Not Found를 반환하게 변경
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String message) {
    super(message);
  }
}
