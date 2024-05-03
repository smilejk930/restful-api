package kr.app.restfulapi.response.error;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

/*
 * URL 요청에 대한 예외처리를 위한 클래스
 * 사용자에게 보여주는 에러 구조
 */
@AllArgsConstructor
@Getter
public class ErrorDetails {

  private LocalDate timestamp;
  private String message;
  private String details;
}
