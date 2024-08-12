package kr.app.restfulapi.domain.common.menu.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import kr.app.restfulapi.domain.common.menu.util.MenuAcsAuthrtType;
import kr.app.restfulapi.domain.common.menu.util.MenuType;
import kr.app.restfulapi.global.entity.BaseAuditingEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sys_menu", uniqueConstraints = {@UniqueConstraint(name = "uix_sys_menu_url_http_meth", columnNames = {"urlAddr", "httpDmndMethNm"})})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Menu extends BaseAuditingEntity {

  @Id
  @Tsid
  @Comment("메뉴식별번호")
  @Column(length = 13, columnDefinition = "CHAR(13)")
  private String menuTsid;

  @Comment("메뉴그룹코드")
  @Column(length = 10, nullable = false)
  private String menuGroupCd;

  @Comment("메뉴명")
  @Column(length = 200, nullable = false)
  private String menuNm;

  @Enumerated(EnumType.STRING)
  @Comment("메뉴유형코드")
  @Column(length = 10, nullable = false)
  private MenuType menuTypeCd;

  @Enumerated(EnumType.STRING)
  @Comment("메뉴접근권한코드")
  @Column(length = 10, nullable = false)
  private MenuAcsAuthrtType menuAcsAuthrtCd;

  @Comment("HTTP요청메소드명")
  @Column(length = 50, nullable = false)
  private String httpDmndMethNm;

  @Comment("URL주소")
  @Column(length = 2000, nullable = false)
  private String urlAddr;

  @Comment("메뉴순서")
  @Column(nullable = false)
  private Long menuSeq;

  @Comment("메뉴설명")
  @Column(length = 4000)
  private String menuExpln;

  @Comment("새창여부")
  @Column(length = 1, nullable = false, columnDefinition = "CHAR(1)")
  @ColumnDefault("'N'")
  @Builder.Default
  private String npagYn = "N";

  @Comment("상위메뉴식별번호")
  @Column(name = "up_menu_tsid", length = 13, columnDefinition = "CHAR(13)")
  private String upMenuTsid;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "up_menu_tsid", foreignKey = @ForeignKey(name = "fk_menu_up_menu_tsid"), insertable = false, updatable = false)
  @JsonBackReference // 부모 엔티티를 참조하는 필드에 설정(순환 참조를 피하기 위해 선언)
  private Menu parent;

  @OneToMany(mappedBy = "parent")
  @JsonManagedReference // 자식 엔티티들을 참조하는 필드에 설정(순환 참조를 피하기 위해 선언)
  private List<Menu> children = new ArrayList<>();

  @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
  @OrderBy("userTypeCd ASC") // userTypeCd를 오름차순으로 정렬
  private Set<MenuAuthrt> menuAuthrts = new HashSet<>();
}
