package kr.app.restfulapi.global.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.benmanes.caffeine.cache.Caffeine;
import kr.app.restfulapi.global.cache.CacheType;

@EnableCaching
@Configuration
public class CacheConfig {

  @Bean
  public CacheManager cacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager();

    for (CacheType cacheType : CacheType.values()) {
      cacheManager.registerCustomCache(cacheType.getCacheName(),
          Caffeine.newBuilder().expireAfterWrite(cacheType.getExpiration(), cacheType.getTimeUnit()).maximumSize(cacheType.getMaxSize()).build());
    }

    return cacheManager;
  }
}
