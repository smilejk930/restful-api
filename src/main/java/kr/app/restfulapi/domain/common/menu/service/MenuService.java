package kr.app.restfulapi.domain.common.menu.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.domain.common.menu.entity.Menu;
import kr.app.restfulapi.domain.common.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {

  private final MenuRepository menuRepository;

  // 이 메서드는 결과가 캐시되어 있지 않은 경우에만 실행
  // TODO 운영 시에 메뉴 캐시 적용
  // @Cacheable(value = CacheNames.RESOURCE_PERMISSIONS, key = "'allResources'")
  @Transactional(readOnly = true)
  public List<Menu> getAllMenu() {
    return menuRepository.findAll();
  }
}
