package kr.app.restfulapi.domain.common.menu.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.domain.common.menu.entity.Menu;
import kr.app.restfulapi.domain.common.menu.repository.MenuRepository;
import kr.app.restfulapi.domain.common.user.gnrl.entity.GnrlUser;
import kr.app.restfulapi.domain.common.user.gnrl.util.UserType;
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
public class MenuService {

  private final MenuRepository menuRepository;

  // 이 메서드는 결과가 캐시되어 있지 않은 경우에만 실행
  // TODO 운영 시에 메뉴 캐시 적용
  // @Cacheable(value = CacheNames.MENU_PERMISSIONS, key = "'allMenus'")
  @Transactional(readOnly = true)
  public List<Menu> getAllMenu() {
    return menuRepository.findAll();
  }

  // @Cacheable(value = CacheNames.MENU_PERMISSIONS, key = "#gnrlUser.lgnId")
  @Transactional(readOnly = true)
  public List<Menu> getUserAccessibleMenus(GnrlUser gnrlUser) {
    List<UserType> userTypes = gnrlUser.getUserAuthrts().stream().map(userAuthrt -> userAuthrt.getUserTypeCd()).toList();

    return menuRepository.findMenuTreeByUserTypes(userTypes);
  }
}
