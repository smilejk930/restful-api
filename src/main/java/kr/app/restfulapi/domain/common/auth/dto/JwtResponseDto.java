package kr.app.restfulapi.domain.common.auth.dto;

public class JwtResponseDto {
  private String accessToken;
  private String tokenType = "Bearer";

  public JwtResponseDto(String accessToken) {
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
