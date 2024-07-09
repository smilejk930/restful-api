package kr.app.restfulapi.response.error;

import java.util.List;
import org.springframework.http.HttpStatusCode;
import kr.app.restfulapi.uga.common.util.CustomDateUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/*
 * URL 요청에 대한 예외처리를 위한 클래스
 * 사용자에게 보여주는 에러 구조
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

  @Builder(builderClassName = "errorStatus", builderMethodName = "errorStatus")
  public ErrorResponse(ErrorStatus status, List<FieldError> errors) {

    this.resultType = status.getResultType();
    this.status = status.getStatus().value();
    this.message = status.getMessage();
    this.timestamp = CustomDateUtils.getFormatCurrentDateTime();
    this.errors = errors;
  }

  @Builder(builderClassName = "httpStatusCode", builderMethodName = "httpStatusCode")
  public ErrorResponse(HttpStatusCode status, List<FieldError> errors) {
    ErrorStatus.find(status);

    this.resultType = ErrorStatus.find(status).getResultType();
    this.status = ErrorStatus.find(status).getStatus().value();
    this.message = ErrorStatus.find(status).getMessage();
    this.timestamp = CustomDateUtils.getFormatCurrentDateTime();
    this.errors = errors;
  }

  private String resultType;
  private Integer status;
  private String message;
  private String timestamp;
  private List<FieldError> errors;
}
