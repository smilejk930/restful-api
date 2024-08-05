package kr.app.restfulapi.domain.common.menu.service;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import kr.app.restfulapi.global.cache.CacheNames;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuCacheService {

  private final CacheManager cacheManager;

  public void clearMenuCache() {
    cacheManager.getCache(CacheNames.MENU_PERMISSIONS).clear();
  }
}
