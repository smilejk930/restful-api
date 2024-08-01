package kr.app.restfulapi.domain.common.user.gnrl.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import kr.app.restfulapi.domain.common.user.gnrl.util.UserAcntSttsType;
import kr.app.restfulapi.global.entity.BaseAuditingEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(indexes = {@Index(name = "idx_user_nm", columnList = "user_nm")},
    uniqueConstraints = {@UniqueConstraint(name = "uix_gnrl_user", columnNames = {"lgn_id"})})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class GnrlUser extends BaseAuditingEntity {

  @Id
  // @Tsid
  // 주석처리: 사용자가입 시 생성된 식별번호로 등록자식별번호를 입력하기 위해 Service 로직에서 직접 생성
  @Comment("사용자식별번호")
  @Column(length = 13, columnDefinition = "CHAR(13)")
  private String userTsid;

  @Comment("업체식별번호")
  @Column(length = 13, columnDefinition = "CHAR(13)")
  private String bzentyTsid;

  @Comment("로그인아이디")
  @Column(length = 50, nullable = false, unique = true)
  private String lgnId;

  @Comment("사용자명")
  @Column(length = 50, nullable = false)
  private String userNm;

  @Enumerated(EnumType.STRING)
  @Comment("사용자계정상태코드")
  @Column(length = 10, nullable = false)
  private UserAcntSttsType userAcntSttsCd;

  @Comment("카드번호")
  @Column(length = 100)
  private String cardNo;

  @Comment("업체관리자여부")
  @Column(length = 1, nullable = false, columnDefinition = "CHAR(1)")
  @ColumnDefault("'N'")
  @Builder.Default
  private String bzentyMngrYn = "N";

  @Comment("비밀번호")
  @Column(length = 200, nullable = false)
  private String pswd;

  @Comment("휴대폰번호")
  @Column(length = 20)
  private String mbtlnum;

  @Comment("이메일주소")
  @Column(length = 100)
  private String emlAddr;

  @Comment("전화번호")
  @Column(length = 20)
  private String telno;

  @Comment("팩스번호")
  @Column(length = 30)
  private String fxno;

  @Comment("가입일시")
  @Column
  private LocalDateTime joinDt;

  @Comment("로그인일시")
  @Column
  private LocalDateTime lgnDt;

  @Comment("로그인실패횟수")
  @Column(nullable = false)
  @ColumnDefault("0")
  @Builder.Default
  private Integer lgnFailNmtm = 0;

  @Comment("로그인잠금여부")
  @Column(length = 1, nullable = false, columnDefinition = "CHAR(1)")
  @ColumnDefault("'N'")
  @Builder.Default
  private String lgnLckYn = "N";

  @Comment("로그인잠금일시")
  @Column
  private LocalDateTime lgnLckDt;

  @Comment("비밀번호변경일자")
  @Column(length = 8)
  private String pswdChgYmd;

  @Comment("휴대폰인증KEY번호")
  @Column(length = 50)
  private String mbtlCertKeyNo;

  @Comment("인증서KEY번호")
  @Column(length = 50)
  private String ahrztKeyNo;

  @Comment("CI번호")
  @Column(length = 50)
  private String ciNo;

  @Comment("DI번호")
  @Column(length = 50)
  private String diNo;

  @Comment("문자메시지수신동의여부")
  @Column(length = 1, nullable = false, columnDefinition = "CHAR(1)")
  @ColumnDefault("'N'")
  @Builder.Default
  private String ltrMsgRcptnAgreYn = "N";

  @Comment("문자메시지수신동의일자")
  @Column(length = 8)
  private String ltrMsgRcptnAgreYmd;

  @Comment("카카오톡수신동의여부")
  @Column(length = 1, nullable = false, columnDefinition = "CHAR(1)")
  @ColumnDefault("'N'")
  @Builder.Default
  private String katalkRcptnAgreYn = "N";

  @Comment("카카오톡수신동의일자")
  @Column(length = 8)
  private String katalkRcptnAgreYmd;

  @Comment("이메일수신동의여부")
  @Column(length = 1, nullable = false, columnDefinition = "CHAR(1)")
  @ColumnDefault("'N'")
  @Builder.Default
  private String emlRcptnAgreYn = "N";

  @Comment("이메일수신동의일자")
  @Column(length = 8)
  private String emlRcptnAgreYmd;

  @Comment("개인정보활용동의여부")
  @Column(length = 1, nullable = false, columnDefinition = "CHAR(1)")
  @ColumnDefault("'N'")
  @Builder.Default
  private String prvcUtlzAgreYn = "N";

  @Comment("개인정보활용동의일자")
  @Column(length = 8)
  private String prvcUtlzAgreYmd;

  @OneToMany(mappedBy = "gnrlUser", fetch = FetchType.LAZY) // CustomUserDetailsService에서 해당 세션이 닫히지 않게 @Transactional(readOnly = true) 설정하여
                                                            // userAuthrts에 접근시 로딩할 수 있게 변경
  @OrderBy("userTypeCd ASC") // userTypeCd를 오름차순으로 정렬
  private Set<UserAuthrt> userAuthrts = new HashSet<>();
}
