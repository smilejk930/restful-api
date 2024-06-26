package kr.app.restfulapi.sample.user.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import kr.app.restfulapi.sample.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "sample_post")
public class Post {

  @Id
  @GeneratedValue
  private Integer id;

  @Size(min = 10, message = "설명은 10글자 이상 입력해주세요.")
  private String description;

  /*
   * FetchType: 관계가 지연 로딩되는지, 즉시 로딩되는지 설정
   * FetchType.EAGER: 즉시 로딩, 동일한 쿼리에서 게시물과 사용자의 세부 정보를 함께 검색된다.(select 한번)
   *                : 문제점) N+1의 문제점이 발생할 수 있음
   * FetchType.LAZY: 지연 로딩
   *               : 게시물을 조회 시 사용자를 프록시 객체로 가져온다.
   *               : 사용자 객체를 조회할 때 DB 조회 쿼리가 사용된다.(select가 게시물 한번, 사용자 한번, 즉 총 2번 사용된다.)
   *               : 실무에서는 가급적 지연 로딩만 사용
   *               : JPQL의 fetch join을 통해서 해당 시점에 한번에 조회할 수 있는 쿼리를 사용할 수 있음
   * ref)https://ict-nroo.tistory.com/132
   */
  @ManyToOne(fetch = FetchType.LAZY) // 게시물은 사용자와 다대일 관계, 사용자는 프록시 객체로 가져온다.
  @JsonIgnore // 응답을 외부로 표현하지 않게 하기 위해 설정
  private User user;
}
