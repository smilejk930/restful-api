package kr.app.restfulapi.domain.common.user.gnrl.entity;

import org.hibernate.annotations.Comment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import kr.app.restfulapi.domain.common.user.gnrl.util.UserType;
import kr.app.restfulapi.global.entity.BaseAuditingEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(indexes = {@Index(name = "ix_user_authrt", columnList = "user_type_cd")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@IdClass(UserAuthrtId.class) // JPA에서 복합키 설정 시 선언
public class UserAuthrt extends BaseAuditingEntity {

  @Id
  @Comment("사용자식별번호")
  private String userTsid;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("userTsid") // 복합 키의 일부로 사용되는 외래 키를 매핑
  @JoinColumn(name = "user_tsid", columnDefinition = "CHAR(13)")
  private GnrlUser gnrlUser;

  @Id
  @Enumerated(EnumType.STRING)
  @Comment("사용자유형코드")
  @Column(length = 10, nullable = false)
  private UserType userTypeCd;
}
