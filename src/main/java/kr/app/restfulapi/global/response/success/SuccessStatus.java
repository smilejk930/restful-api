package kr.app.restfulapi.response.success;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessStatus {

  OK(
      HttpStatus.OK, "성공입니다."
  ),
  CREATED(
      HttpStatus.CREATED, "정상적으로 등록되었습니다."
  ),
  UPDATED(
      HttpStatus.OK, "정상적으로 수정되었습니다."
  ),
  DELETED(
      HttpStatus.OK, "정상적으로 삭제되었습니다."
  ),
  NO_CONTENT(
      HttpStatus.NO_CONTENT, "정보가 존재하지 않습니다."
  ),
  ACCEPTED(
      HttpStatus.ACCEPTED, "요청이 접수되어 처리 중입니다."
  );

  private final String resultType;
  private final HttpStatus status;
  private final String message;

  SuccessStatus(HttpStatus status, String message) {
    this("success", status, message);
  }

  private static final Map<HttpStatus, SuccessStatus> lookup = new HashMap<>();
  static {
    for (SuccessStatus e : SuccessStatus.values()) {
      lookup.put(e.getStatus(), e);
    }
  }

  public static SuccessStatus find(HttpStatusCode status) {
    return lookup.get(status);
  }
}
