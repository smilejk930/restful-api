package kr.app.restfulapi.response.error;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorStatus {

  INTERNAL_SERVER_ERROR(
      HttpStatus.INTERNAL_SERVER_ERROR, "서비스 제공 상태가 원활하지 않습니다."
  ),
  METHOD_NOT_ALLOWED(
      HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP METHOD입니다."
  ),
  BAD_REQUEST(
      "fail", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."
  ),
  HTTP_MESSAGE_NOT_READABLE(
      "fail", HttpStatus.BAD_REQUEST, "BODY에 입력한 JSON 형식이 잘못되었습니다."
  ),
  UNAUTHORIZED(
      HttpStatus.UNAUTHORIZED, "인증이 필요합니다."
  ),
  FORBIDDEN(
      HttpStatus.FORBIDDEN, "접근이 금지되었습니다."
  ),
  NOT_FOUND(
      HttpStatus.NOT_FOUND, "요청한 자원을 찾을 수 없습니다."
  ),
  CONFLICT(
      HttpStatus.CONFLICT, "데이터 충돌이 발생했습니다."
  ),
  GONE(
      HttpStatus.GONE, "요청한 자원이 더 이상 존재하지 않습니다."
  ),
  UNSUPPORTED_MEDIA_TYPE(
      HttpStatus.UNSUPPORTED_MEDIA_TYPE, "지원되지 않는 미디어 타입입니다."
  ),
  UNPROCESSABLE_ENTITY(
      HttpStatus.UNPROCESSABLE_ENTITY, "요청한 데이터를 처리할 수 없습니다."
  ),
  TOO_MANY_REQUESTS(
      HttpStatus.TOO_MANY_REQUESTS, "요청이 너무 많습니다. 잠시 후 다시 시도해주세요."
  );

  private final String resultType;
  private final HttpStatus status;
  private final String message;

  ErrorStatus(HttpStatus status, String message) {
    this("error", status, message);
  }

  private static final Map<HttpStatus, ErrorStatus> lookup = new HashMap<>();
  static {
    for (ErrorStatus e : ErrorStatus.values()) {
      lookup.put(e.getStatus(), e);
    }
  }

  public static ErrorStatus find(HttpStatusCode status) {
    return lookup.get(status);
  }
}
