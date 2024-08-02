package kr.app.restfulapi.domain.admin.menu.dto;

import java.util.List;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kr.app.restfulapi.domain.common.menu.entity.Menu;
import kr.app.restfulapi.domain.common.menu.entity.MenuAuthrt;
import kr.app.restfulapi.domain.common.menu.util.MenuAcsAuthrtType;
import kr.app.restfulapi.domain.common.menu.util.MenuType;
import kr.app.restfulapi.domain.common.user.gnrl.util.UserType;

public record MenuMngReqstDto(
    @NotBlank(message = "메뉴그룹코드는 필수 입력값입니다.")
    @Size(max = 10, message = "메뉴그룹코드는 {max}자 이하로 입력해주세요.") String menuGroupCd,

    @NotBlank(message = "메뉴명은 필수 입력값입니다.")
    @Size(min = 3, max = 200, message = "메뉴명은 {min}자 이상, {max}자 이하로 입력해주세요.") String menuNm,

    @NotEmpty(message = "메뉴유형코드는 필수 입력값입니다.") MenuType menuTypeCd,

    @NotEmpty(message = "메뉴접근권한코드는 필수 입력값입니다.") MenuAcsAuthrtType menuAcsAuthrtCd,

    @NotBlank(message = "HTTP요청메소드명은 필수 입력값입니다.")
    @Pattern(regexp = "GET|POST|PUT|DELETE", message = "유효한 HTTP요청메소드명을 입력해주세요 (GET, POST, PUT, DELETE).") String httpDmndMethNm,

    @NotBlank(message = "URL주소는 필수 입력값입니다.")
    @Size(min = 1, max = 2000, message = "URL주소는 {min}자 이상, {max}자 이하로 입력해주세요.") String urlAddr,

    @Min(value = 1, message = "메뉴순서는 최소 {value}이어야 합니다.") Long menuSeq,

    String menuExpln,

    // @Size(min = 13, max = 13, message = "상위메뉴식별번호는 정확히 {max}자 입니다.")
    String upMenuTsid,

    @NotBlank(message = "새창여부는 필수 입력값입니다.")
    @Size(max = 1, message = "새창여부는 {max}자 입니다.")
    @Pattern(regexp = "[YN]", message = "새창여부는 Y 또는 N 이어야 합니다.") String npagYn,

    @NotEmpty(message = "사용자유형은 1개 이상 선택해야 합니다.") List<UserType> userTypeCds) {

  public Menu toEntity() {
    return Menu.builder()
        .menuGroupCd(menuGroupCd)
        .menuNm(menuNm)
        .menuTypeCd(menuTypeCd)
        .menuAcsAuthrtCd(menuAcsAuthrtCd)
        .httpDmndMethNm(httpDmndMethNm)
        .urlAddr(urlAddr)
        .menuSeq(menuSeq)
        .menuExpln(menuExpln)
        .upMenuTsid(upMenuTsid)
        .npagYn(npagYn)
        .build();
  }

  public List<MenuAuthrt> toMenuAuthrtEntities(Menu menu) {
    return userTypeCds.stream()
        .map(userTypeCd -> MenuAuthrt.builder().menuTsid(menu.getMenuTsid()).menu(menu).userTypeCd(userTypeCd).menuGroupCd(menuGroupCd).build())
        .toList();
  }
}
