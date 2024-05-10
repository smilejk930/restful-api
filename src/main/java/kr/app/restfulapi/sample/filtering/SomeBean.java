package kr.app.restfulapi.sample.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
// @JsonIgnoreProperties({"field1", "field2"})
public class SomeBean {
  @JsonIgnore // response의 필드를 정적으로 표시되지 않게 설정
  private String field1;

  @JsonProperty("field_2") // response 필드명을 custom할 수 있음
  private String field2;
  private String field3;
}
