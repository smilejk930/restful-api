package kr.app.restfulapi.global.cache;

import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CacheType {

  RESOURCE_PERMISSIONS(
      CacheNames.RESOURCE_PERMISSIONS, 1, TimeUnit.HOURS, 10000
  ),
  USER_INFO(
      CacheNames.USER_INFO, 30, TimeUnit.MINUTES, 1000
  ),
  OTHER_CACHE(
      CacheNames.OTHER_CACHE, 15, TimeUnit.MINUTES, 200
  );

  // 캐시이름(자주 접근하는 정보를 메모리에 캐시하여 데이터베이스 조회를 줄임)
  private final String cacheName;

  // 캐시 항목이 작성된 후 expiration 후에 만료되도록 설정(1시간마다 캐시를 갱신하여 데이터의 최신성을 유지)
  private final int expiration;

  private final TimeUnit timeUnit;

  // 캐시의 최대 항목 수를 cacheMaxSize개로 제한(캐시 크기를 제한하여 메모리 사용을 제어)
  private final int maxSize;

}
