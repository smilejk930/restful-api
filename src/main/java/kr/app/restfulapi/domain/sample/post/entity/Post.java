package kr.app.restfulapi.domain.sample.post.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import kr.app.restfulapi.global.entity.BaseAuditingEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post",
    // uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id"}, name = "fk_user_id")},
    indexes = {@Index(name = "idx_sj", columnList = "sj"), @Index(name = "idx_regist_dt", columnList = "regist_dt")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post extends BaseAuditingEntity {

  @Comment("게시글아이디")
  @Id
  // Resolved [java.lang.NullPointerException: Cannot invoke "cubrid.jdbc.driver.CUBRIDConnection.createCUBRIDException(int, java.lang.Throwable)"
  // because "this.con" is null]
  // @GeneratedValue(strategy = GenerationType.IDENTITY) //cubrid error 발생
  // @GeneratedValue(strategy = GenerationType.AUTO)
  // @GeneratedValue(strategy = GenerationType.UUID)
  @Tsid
  @Column(length = 13, columnDefinition = "CHAR(13)")
  private String postId;

  @Comment("제목")
  @Column(length = 200, nullable = false)
  private String sj;

  @Comment("내용")
  @Column(length = 4000, nullable = false)
  private String cn;

  @Comment("삭제여부")
  @ColumnDefault("'N'")
  @Column(length = 1, nullable = false)
  @Builder.Default
  private String deleteAt = "N";

  @Comment("전문길이")
  @Column(columnDefinition = "NUMERIC(10)")
  private Integer telgmLen;

  @Comment("제출여부")
  @ColumnDefault("'N'")
  @Column(length = 1, nullable = false)
  @Builder.Default
  private String sbmsnYn = "N";

  @Comment("제출일시")
  private LocalDateTime sbmsnDt;

  @Transient // JPA가 이 필드를 데이터베이스 컬럼으로 인식하지 않도록 하기 위해 @Transient를 사용
  private String userNm;

  // @Comment("사용자아이디")
  // @ManyToOne(fetch = FetchType.LAZY)
  // @JoinColumn(name = "user_id",
  // name = "wrter_id", referencedColumnName = "userId",
  // foreignKey = @ForeignKey(name = "fk_user_info_to_post"))
  // private User user;
}
