package kr.app.restfulapi.domain.common.code.entity;

import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import kr.app.restfulapi.domain.admin.code.dto.CdGroupMngSrchDto;
import kr.app.restfulapi.domain.common.code.dto.CdGroupSrchDto;
import kr.app.restfulapi.global.entity.BaseAuditingEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sys_cd_group")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CdGroup extends BaseAuditingEntity {

  @Id
  @Comment("코드그룹명")
  @Column(length = 100, nullable = false)
  private String cdGroupNm;

  @Comment("코드한글명")
  @Column(length = 50, nullable = false)
  private String cdKornNm;

  @Comment("코드구분명")
  @Column(length = 100, nullable = false)
  private String cdSeNm;

  @Comment("코드설명")
  @Column(length = 200)
  private String cdExpln;

  @Comment("사용여부")
  @Column(length = 1, nullable = false, columnDefinition = "CHAR(1)")
  @ColumnDefault("'Y'")
  @Builder.Default
  private String useYn = "Y";

  @Comment("공통코드여부")
  @Column(length = 1, nullable = false, columnDefinition = "CHAR(1)")
  @ColumnDefault("'N'")
  @Builder.Default
  private String comCdYn = "N";

  @OneToMany(mappedBy = "cdGroup", fetch = FetchType.LAZY)
  @OrderBy("cdSeq ASC") // cdSeq를 오름차순으로 정렬
  private Set<Cd> cds = new HashSet<>();

  @Transient
  private CdGroupSrchDto srchDto;

  @Transient
  private CdGroupMngSrchDto mngSrchDto;
}
