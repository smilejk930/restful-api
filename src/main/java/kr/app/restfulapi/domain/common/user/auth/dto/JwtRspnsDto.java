package kr.app.restfulapi.domain.common.user.auth.dto;

public class JwtRspnsDto {
  private String accessToken;
  private String tokenType = "Bearer";

  public JwtRspnsDto(String accessToken) {
    this.accessToken = accessToken;
  }

  // Getter and Setter for accessToken
  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  // Getter for tokenType
  public String getTokenType() {
    return tokenType;
  }
}
