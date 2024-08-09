package kr.app.restfulapi.domain.admin.menu.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.CacheEvict;
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
import kr.app.restfulapi.global.cache.CacheNames;
import kr.app.restfulapi.global.response.error.exception.DuplicateKeyException;
import kr.app.restfulapi.global.response.error.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * cache 사용
 * - 성능 향상: 자주 접근하는 메뉴의 권한 정보를 캐시에 저장하여 데이터베이스 조회 횟수를 줄임
 * - 부하 감소: 데이터베이스 서버의 부하를 줄임
 * - 응답 시간 개선: 캐시된 데이터를 사용하므로 권한 체크 속도가 빨라짐
 * - 유연성: 캐시 설정(예: 만료 시간, 최대 크기)을 조정하여 애플리케이션의 요구사항에 맞출 수 있음
 * - 캐시 일관성: 메뉴 권한이 변경될 때 캐시를 적절히 갱신해야 함
 * 이를 위해 clearMenuCache() 메서드를 제공하고, 권한 변경 시 이 메서드를 호출하여 캐시를 갱신
 */
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

  // 이 메서드의 결과는 항상 캐시를 갱신
  // @CachePut(value = CacheNames.MENU_PERMISSIONS, key = "#menuMngReqstDto.httpDmndMethNm + '-' + #menuMngReqstDto.urlAddr")
  @Transactional
  public MenuMngRspnsDto createMenu(String menuGroupCd, MenuMngReqstDto menuMngReqstDto) {
    if (menuRepository.findByHttpDmndMethNmAndUrlAddr(menuMngReqstDto.getHttpDmndMethNm(), menuMngReqstDto.getUrlAddr()).isPresent()) {
      throw new DuplicateKeyException("입력한 HTTP요청메소드명과 URL주소 존재합니다.");
    }

    if (menuMngReqstDto.getMenuAcsAuthrtCd() == MenuAcsAuthrtType.MAA001 && menuMngReqstDto.getUserTypeCds().isEmpty()) {
      throw new IllegalArgumentException("사용자유형은 1개 이상 선택해야 합니다.");
    }

    Menu menu = menuMngReqstDto.toEntity();
    menu.setMenuGroupCd(menuGroupCd);

    Menu savedMenu = menuRepository.save(menu);

    if (menuMngReqstDto.getMenuAcsAuthrtCd() == MenuAcsAuthrtType.MAA001) {
      // 메뉴권한에 사용자유형 등록
      List<MenuAuthrt> menuAuthrts = menuMngReqstDto.toMenuAuthrtEntities(savedMenu);
      menuAuthrts.forEach(menuAuthrtRepository::save);
      savedMenu.setMenuAuthrts(new HashSet<>(menuAuthrts));
    }

    return MenuMngRspnsDto.toDto(savedMenu);
  }

  // 이 메서드의 결과는 항상 캐시를 갱신
  // @CachePut(value = CacheNames.MENU_PERMISSIONS, key = "#menuMngReqstDto.httpDmndMethNm + '-' + #menuMngReqstDto.urlAddr")
  @Transactional
  public Optional<MenuMngRspnsDto> updateMenu(String menuTsid, MenuMngReqstDto menuMngReqstDto) {

    Menu menu = menuRepository.findByMenuTsid(menuTsid).orElseThrow(ResourceNotFoundException::new);

    menu.setMenuGroupCd(menuMngReqstDto.getMenuGroupCd());
    menu.setMenuNm(menuMngReqstDto.getMenuNm());
    menu.setMenuTypeCd(menuMngReqstDto.getMenuTypeCd());
    menu.setMenuAcsAuthrtCd(menuMngReqstDto.getMenuAcsAuthrtCd());
    menu.setHttpDmndMethNm(menuMngReqstDto.getHttpDmndMethNm());
    menu.setUrlAddr(menuMngReqstDto.getUrlAddr());
    menu.setMenuSeq(menuMngReqstDto.getMenuSeq());
    menu.setMenuExpln(menuMngReqstDto.getMenuExpln());
    menu.setUpMenuTsid(menuMngReqstDto.getUpMenuTsid());
    menu.setNpagYn(menuMngReqstDto.getNpagYn());

    Menu updatedMenu = menuRepository.save(menu);

    // 메뉴권한에서 사용자식별번호에 대한 사용자유형 삭제 후 재등록
    menuAuthrtRepository.deleteAllByMenuTsid(updatedMenu.getMenuTsid());

    List<MenuAuthrt> menuAuthrts = menuMngReqstDto.toMenuAuthrtEntities(updatedMenu);
    menuAuthrts.forEach(menuAuthrtRepository::save);

    updatedMenu.setMenuAuthrts(new HashSet<>(menuAuthrts));

    return Optional.of(MenuMngRspnsDto.toDto(updatedMenu));
  }

  // 이 메서드는 전체 캐시를 비움
  @CacheEvict(value = CacheNames.MENU_PERMISSIONS, allEntries = true)
  public void clearMenuCache() {

  }
}
