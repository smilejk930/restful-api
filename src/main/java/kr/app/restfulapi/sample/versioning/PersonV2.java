package kr.app.restfulapi.sample.versioning;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class PersonV2 {
  private Name name;
}
