package kr.app.restfulapi.domain.admin.menu.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.domain.admin.menu.dto.MenuMngReqstDto;
import kr.app.restfulapi.domain.admin.menu.dto.MenuMngRspnsDto;
import kr.app.restfulapi.domain.common.menu.entity.Menu;
import kr.app.restfulapi.domain.common.menu.entity.MenuAuthrt;
import kr.app.restfulapi.domain.common.menu.repository.MenuAuthrtRepository;
import kr.app.restfulapi.domain.common.menu.repository.MenuRepository;
import kr.app.restfulapi.domain.common.menu.util.MenuAcsAuthrtType;
import kr.app.restfulapi.global.response.error.exception.DuplicateKeyException;
import kr.app.restfulapi.global.response.error.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuMngService {

  private final MenuRepository menuRepository;
  private final MenuAuthrtRepository menuAuthrtRepository;

  @Transactional(readOnly = true)
  public List<MenuMngRspnsDto> getAllMenuByMenuGroupCd(String menuGroupCd) {

    Sort sort = Sort.by(Sort.Order.asc("upMenuTsid"), Sort.Order.asc("menuSeq"));
    return menuRepository.findAllByMenuGroupCd(menuGroupCd, sort).stream().map(MenuMngRspnsDto::toDto).toList();
  }

  @Transactional
  public MenuMngRspnsDto createMenu(String menuGroupCd, MenuMngReqstDto menuMngReqstDto) {
    if (menuRepository.findByHttpDmndMethNmAndUrlAddr(menuMngReqstDto.httpDmndMethNm(), menuMngReqstDto.urlAddr()).isPresent()) {
      throw new DuplicateKeyException("입력한 HTTP요청메소드명과 URL주소 존재합니다.");
    }

    if (menuMngReqstDto.menuAcsAuthrtCd() == MenuAcsAuthrtType.MAA001 && menuMngReqstDto.userTypeCds().isEmpty()) {
      throw new IllegalArgumentException("사용자유형은 1개 이상 선택해야 합니다.");
    }

    Menu menu = menuMngReqstDto.toEntity();
    menu.setMenuGroupCd(menuGroupCd);

    Menu savedMenu = menuRepository.save(menu);

    if (menuMngReqstDto.menuAcsAuthrtCd() == MenuAcsAuthrtType.MAA001) {
      // 메뉴권한에 사용자유형 등록
      List<MenuAuthrt> menuAuthrts = menuMngReqstDto.toMenuAuthrtEntities(savedMenu);
      menuAuthrts.forEach(menuAuthrtRepository::save);
      savedMenu.setMenuAuthrts(new HashSet<>(menuAuthrts));
    }

    return MenuMngRspnsDto.toDto(savedMenu);
  }

  @Transactional
  public Optional<MenuMngRspnsDto> updateMenu(String menuTsid, MenuMngReqstDto menuMngReqstDto) {

    Menu menu = menuRepository.findByMenuTsid(menuTsid).orElseThrow(ResourceNotFoundException::new);

    menu.setMenuGroupCd(menuMngReqstDto.menuGroupCd());
    menu.setMenuNm(menuMngReqstDto.menuNm());
    menu.setMenuTypeCd(menuMngReqstDto.menuTypeCd());
    menu.setMenuAcsAuthrtCd(menuMngReqstDto.menuAcsAuthrtCd());
    menu.setHttpDmndMethNm(menuMngReqstDto.httpDmndMethNm());
    menu.setUrlAddr(menuMngReqstDto.urlAddr());
    menu.setMenuSeq(menuMngReqstDto.menuSeq());
    menu.setMenuExpln(menuMngReqstDto.menuExpln());
    menu.setUpMenuTsid(menuMngReqstDto.upMenuTsid());
    menu.setNpagYn(menuMngReqstDto.npagYn());

    Menu updatedMenu = menuRepository.save(menu);

    // 메뉴권한에서 사용자식별번호에 대한 사용자유형 삭제 후 재등록
    menuAuthrtRepository.deleteAllByMenuTsid(updatedMenu.getMenuTsid());

    List<MenuAuthrt> menuAuthrts = menuMngReqstDto.toMenuAuthrtEntities(updatedMenu);
    menuAuthrts.forEach(menuAuthrtRepository::save);

    updatedMenu.setMenuAuthrts(new HashSet<>(menuAuthrts));

    return Optional.of(MenuMngRspnsDto.toDto(updatedMenu));
  }
}
