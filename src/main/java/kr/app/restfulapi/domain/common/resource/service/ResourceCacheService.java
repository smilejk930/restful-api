package kr.app.restfulapi.domain.common.resource.service;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import kr.app.restfulapi.global.cache.CacheNames;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResourceCacheService {

  private final CacheManager cacheManager;

  public void clearResourceCache() {
    cacheManager.getCache(CacheNames.RESOURCE_PERMISSIONS).clear();
  }
}
