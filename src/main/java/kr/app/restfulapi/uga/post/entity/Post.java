package kr.app.restfulapi.uga.post.entity;

import org.hibernate.annotations.Comment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.app.restfulapi.uga.common.entity.BaseEntity;
import kr.app.restfulapi.uga.user.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post", schema = "TESTUSER",
    // uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id"}, name = "fk_user_id")},
    indexes = {@Index(name = "idx_sj", columnList = "sj"),
        @Index(name = "idx_regist_dt", columnList = "regist_dt")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {

  @Comment("게시글아이디")
  @Id
  // Resolved [java.lang.NullPointerException: Cannot invoke "cubrid.jdbc.driver.CUBRIDConnection.createCUBRIDException(int, java.lang.Throwable)"
  // because "this.con" is null]
  // @GeneratedValue(strategy = GenerationType.IDENTITY) //cubrid error 발생
  // @GeneratedValue(strategy = GenerationType.AUTO)
  @GeneratedValue(strategy = GenerationType.UUID)
  private String postId;

  @Comment("제목")
  @Column(length = 200, nullable = false)
  private String sj;

  @Comment("내용")
  @Column(length = 4000, nullable = false)
  private String cn;

  @Comment("사용자아이디")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id",
      // name = "wrter_id", referencedColumnName = "userId",
      foreignKey = @ForeignKey(name = "fk_user_info_to_post"))
  private User user;
}
