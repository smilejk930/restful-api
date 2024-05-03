package kr.app.restfulapi.sample.versioning;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Name {
  private String firstName;
  private String lastName;
}
