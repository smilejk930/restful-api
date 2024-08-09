package kr.app.restfulapi.domain.sample.post.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestDto {

  private String fileGroupNm;
  private String fileClsfNm;
}
