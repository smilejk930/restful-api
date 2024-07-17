package kr.app.restfulapi.domain.common.user.entity;

import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Comment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import kr.app.restfulapi.domain.common.role.entity.Role;
import kr.app.restfulapi.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users", indexes = {@Index(name = "idx_user_nm", columnList = "user_nm")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Comment("사용자아이디")
  private String userId;

  @Comment("로그인아이디")
  @Column(length = 20, nullable = false, unique = true)
  private String loginId;

  @Comment("사용자명")
  @Column(length = 100, nullable = false)
  private String userNm;

  @Comment("비밀번호")
  @Column(length = 100, nullable = false)
  private String password;

  @ManyToMany(fetch = FetchType.EAGER) // TODO LAZY했을때 CustomUserDetailsService의 optUser.map(UserPrincipal::create)에서 에러 발생 추후 확인 바람
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  /**
   * LAZY 로딩으로 변경한 후에는 다음 사항들을 고려해야 합니다:
   * Role 정보가 필요한 경우, 명시적으로 로드하거나 JPQL/QueryDSL에서 join fetch를 사용합니다.
   */
  // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  // private List<Post> post;
}
