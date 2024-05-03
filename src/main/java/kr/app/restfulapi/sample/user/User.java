package kr.app.restfulapi.sample.user;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class User {
  private Integer id;
  private String name;
  private LocalDate birthDate;
}
