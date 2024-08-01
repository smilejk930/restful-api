package kr.app.restfulapi.domain.common.code.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.app.restfulapi.global.entity.BaseAuditingEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Cd extends BaseAuditingEntity {

  @Id
  @Comment("코드그룹명")
  @ManyToOne
  @JoinColumn(name = "cd_group_nm", nullable = false)
  private CdGroup cdGroup;

  @Id
  @Comment("코드명")
  @Column(length = 50, nullable = false)
  private String cdNm;

  @Comment("코드한글명")
  @Column(length = 50, nullable = false)
  private String cdKornNm;

  @Comment("상위코드명")
  @Column(length = 50)
  private String upCdNm;

  @Comment("코드순서")
  @Column(nullable = false)
  private Integer cdSeq;

  @Comment("코드설명")
  @Column(length = 200)
  private String cdExpln;

  @Comment("사용여부")
  @Column(length = 1, nullable = false, columnDefinition = "CHAR(1)")
  @ColumnDefault("'Y'")
  @Builder.Default
  private String useYn = "Y";
}
