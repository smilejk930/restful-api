package kr.app.restfulapi.domain.common.code.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import kr.app.restfulapi.domain.admin.code.dto.CdMngSrchDto;
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
@IdClass(CdId.class) // JPA에서 복합키 설정 시 선언
public class Cd extends BaseAuditingEntity {

  @Id
  @Comment("코드그룹명")
  private String cdGroupNm;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("cdGroupNm") // 복합 키의 일부로 사용되는 외래 키를 매핑
  @JoinColumn(name = "cd_group_nm", columnDefinition = "VARCHAR(100)")
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

  @Transient
  private CdMngSrchDto mngSrchDto;
}
