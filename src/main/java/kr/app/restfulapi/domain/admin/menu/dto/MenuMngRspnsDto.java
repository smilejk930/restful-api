package kr.app.restfulapi.domain.admin.menu.dto;

import java.util.List;
import kr.app.restfulapi.domain.common.menu.entity.Menu;
import kr.app.restfulapi.domain.common.menu.entity.MenuAuthrt;
import kr.app.restfulapi.domain.common.menu.util.MenuAcsAuthrtType;
import kr.app.restfulapi.domain.common.menu.util.MenuType;
import kr.app.restfulapi.global.util.CustomDateUtils;

public record MenuMngRspnsDto(
    String menuTsid,
    String menuGroupCd,
    String menuNm,
    MenuType menuTypeCd,
    MenuAcsAuthrtType menuAcsAuthrtCd,
    String httpDmndMethNm,
    String urlAddr,
    Long menuSeq,
    String menuExpln,
    String npagYn,
    String upMenuTsid,
    Menu parent,
    List<Menu> children,
    List<MenuAuthrt> menuAuthrts,
    String regYmd) {

  public static MenuMngRspnsDto toDto(Menu menu) {
    return new MenuMngRspnsDto(
        menu.getMenuTsid(),
        menu.getMenuGroupCd(),
        menu.getMenuNm(),
        menu.getMenuTypeCd(),
        menu.getMenuAcsAuthrtCd(),
        menu.getHttpDmndMethNm(),
        menu.getUrlAddr(),
        menu.getMenuSeq(),
        menu.getMenuExpln(),
        menu.getNpagYn(),
        menu.getUpMenuTsid(),
        menu.getParent(),
        menu.getChildren(),
        menu.getMenuAuthrts().stream().toList(),
        CustomDateUtils.getFormatYmdDate(menu.getRegDt()));
  }
}
