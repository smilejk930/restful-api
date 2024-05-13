package kr.app.restfulapi.sample.user;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity(name = "user_details")
public class User {

  @Id
  @GeneratedValue
  private Integer id;

  @Size(min = 2, message = "이름은 2글자 이상 입력해주세요.")
  private String name;

  // past는 현재 날짜보다 과거인지를 검증하는 어노테이션입니다.
  @Past(message = "생년월일은 과거 날짜만 입력 가능합니다.")
  private LocalDate birthDate;
}
