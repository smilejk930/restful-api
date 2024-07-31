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
public class GroupCd extends BaseAuditingEntity {

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

  @OneToMany(mappedBy = "groupCd", fetch = FetchType.LAZY)
  private Set<ComCd> comCds = new HashSet<>();
}
