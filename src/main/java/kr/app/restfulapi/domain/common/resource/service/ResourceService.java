package kr.app.restfulapi.domain.common.resource.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.domain.common.resource.entity.Resource;
import kr.app.restfulapi.domain.common.resource.repository.ResourceRepository;
import kr.app.restfulapi.domain.common.role.entity.Role;
import kr.app.restfulapi.domain.common.user.entity.User;
import kr.app.restfulapi.global.cache.CacheNames;
import lombok.RequiredArgsConstructor;

/**
 * cache 사용
 * - 성능 향상: 자주 접근하는 리소스의 권한 정보를 캐시에 저장하여 데이터베이스 조회 횟수를 줄임
 * - 부하 감소: 데이터베이스 서버의 부하를 줄임
 * - 응답 시간 개선: 캐시된 데이터를 사용하므로 권한 체크 속도가 빨라짐
 * - 유연성: 캐시 설정(예: 만료 시간, 최대 크기)을 조정하여 애플리케이션의 요구사항에 맞출 수 있음
 * - 캐시 일관성: 리소스 권한이 변경될 때 캐시를 적절히 갱신해야 함
 * 이를 위해 clearResourceCache() 메서드를 제공하고, 권한 변경 시 이 메서드를 호출하여 캐시를 갱신
 */
@Service
@RequiredArgsConstructor
public class ResourceService {

  // TODO Throw 로직 추가해야함

  private final ResourceRepository resourceRepository;

  // 이 메서드는 결과가 캐시되어 있지 않은 경우에만 실행
  @Cacheable(value = CacheNames.RESOURCE_PERMISSIONS, key = "#urlPattern + '-' + #method")
  @Transactional(readOnly = true)
  public Optional<Resource> findByUrlPatternAndMethod(String urlPattern, String method) {
    return resourceRepository.findByUrlPatternAndMethod(urlPattern, method);
  }

  // 이 메서드의 결과는 항상 캐시를 갱신
  @CachePut(value = CacheNames.RESOURCE_PERMISSIONS, key = "#resource.urlPattern + '-' + #resource.method")
  @Transactional
  public Resource save(Resource resource) {
    return resourceRepository.save(resource);
  }

  // 이 메서드가 실행되면 해당 캐시 항목을 제거
  @CacheEvict(value = CacheNames.RESOURCE_PERMISSIONS, key = "#resource.urlPattern + '-' + #resource.method")
  @Transactional
  public void delete(Resource resource) {
    resourceRepository.delete(resource);
  }

  // 이 메서드는 전체 캐시를 비움
  @CacheEvict(value = CacheNames.RESOURCE_PERMISSIONS, allEntries = true)
  public void clearCache() {

  }

  @Cacheable(value = CacheNames.RESOURCE_PERMISSIONS, key = "#user.loginId")
  @Transactional(readOnly = true)
  public List<Resource> getUserAccessibleResources(User user) {
    List<String> userRoles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());

    return resourceRepository.findResourceTreeByUserRoles(userRoles);
  }
}