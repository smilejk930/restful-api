package kr.app.restfulapi.uga.file.entity;

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
@Table(schema = "TESTUSER", indexes = {@Index(name = "idx_finm", columnList = "finm"), @Index(name = "idx_regist_dt", columnList = "regist_dt")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FileData extends BaseEntity {

  @Comment("파일아이디")
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String fileId;

  @Comment("파일명")
  @Column(length = 200, nullable = false)
  private String finm;

  @Comment("파일저장명")
  @Column(length = 200, nullable = false)
  private String fileStreNm;

  @Comment("파일경로")
  @Column(length = 4000, nullable = false)
  private String flpth;

  @Comment("파일사이즈")
  @Column(nullable = false)
  private Long filesiz;

  @Comment("파일확장자명")
  @Column(length = 200, nullable = false)
  private String fileEventn;

  @Comment("사용자아이디")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id",
      // name = "wrter_id", referencedColumnName = "userId",
      foreignKey = @ForeignKey(name = "fk_user_info_to_files"))
  private User user;
}
