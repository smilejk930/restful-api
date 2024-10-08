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
import kr.app.restfulapi.domain.sample.post.dto.PostSrchDto;
import kr.app.restfulapi.global.entity.BaseAuditingEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_post", indexes = {@Index(name = "ix_t_post_ttl", columnList = "ttl"), @Index(name = "ix_t_post_reg_dt", columnList = "reg_dt")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post extends BaseAuditingEntity {

  @Id
  @Tsid
  @Comment("게시글식별번호")
  @Column(length = 13, columnDefinition = "CHAR(13)")
  private String postTsid;

  @Comment("제목")
  @Column(length = 500, nullable = false)
  private String ttl;

  @Comment("내용")
  @Column(length = 4000)
  private String cn;

  @Comment("삭제여부")
  @Column(length = 1, nullable = false, columnDefinition = "CHAR(1)")
  @ColumnDefault("'N'")
  @Builder.Default
  private String delYn = "N";

  @Comment("전문길이")
  @Column(columnDefinition = "NUMERIC(10)")
  private Integer telgmLen;

  @Comment("제출여부")
  @Column(length = 1, nullable = false, columnDefinition = "CHAR(1)")
  @ColumnDefault("'N'")
  @Builder.Default
  private String sbmsnYn = "N";

  @Comment("제출일시")
  private LocalDateTime sbmsnDt;

  @Transient // JPA가 이 필드를 데이터베이스 컬럼으로 인식하지 않도록 하기 위해 @Transient를 사용
  private String rgtrNm;// 등록자명

  @Transient
  private PostSrchDto srchDto;
}
