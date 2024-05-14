package kr.app.restfulapi.sample.user;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import kr.app.restfulapi.sample.user.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
// @AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "user_details")
public class User {

  @Id
  @GeneratedValue
  private Integer id;

  @Size(min = 2, message = "이름은 2글자 이상 입력해주세요.")
  // @JsonProperty("user_name")
  private String name;

  // past는 현재 날짜보다 과거인지를 검증하는 어노테이션입니다.
  @Past(message = "생년월일은 과거 날짜만 입력 가능합니다.")
  // @JsonProperty("birth_date")
  private LocalDate birthDate;

  @OneToMany(mappedBy = "user") // 속성으로 특정 관계를 가지는 필드의 매핑설정
  @JsonIgnore // 응답을 외부로 표현하지 않게 하기 위해 설정
  private List<Post> posts; // 사용자는 게시물과 일대다 관계

  public User(Integer id, @Size(min = 2, message = "이름은 2글자 이상 입력해주세요.") String name,
      @Past(message = "생년월일은 과거 날짜만 입력 가능합니다.") LocalDate birthDate) {
    super();
    this.id = id;
    this.name = name;
    this.birthDate = birthDate;
  }


}
