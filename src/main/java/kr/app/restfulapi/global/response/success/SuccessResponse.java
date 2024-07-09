package kr.app.restfulapi.response.success;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SuccessResponse {

  @Builder
  public SuccessResponse(SuccessStatus status, Object data) {
    this.resultType = status.getResultType();
    this.status = status.getStatus().value();
    this.message = status.getMessage();
    this.data = data;
  }

  private String resultType;
  private Integer status;
  private String message;
  private Object data;
}
