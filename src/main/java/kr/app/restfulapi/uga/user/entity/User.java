package kr.app.restfulapi.uga.user.entity;

import java.util.List;
import org.hibernate.annotations.Comment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kr.app.restfulapi.uga.common.entity.BaseEntity;
import kr.app.restfulapi.uga.post.entity.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_info", schema = "TESTUSER", indexes = {@Index(name = "idx_user_nm", columnList = "user_nm")})
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
  @Column(length = 20, nullable = false)
  private String loginId;

  @Comment("사용자명")
  @Column(length = 100, nullable = false)
  private String userNm;

  @Comment("비밀번호")
  @Column(length = 20, nullable = false)
  private String password;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Post> post;
}
