package kr.app.restfulapi.domain.common.resource.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.app.restfulapi.domain.common.resource.entity.QResource;
import kr.app.restfulapi.domain.common.resource.entity.Resource;
import kr.app.restfulapi.domain.common.role.entity.QRole;
import lombok.RequiredArgsConstructor;

/**
 * 어플리케이션 레벨에서의 트리구성
 * 이 구현의 장점:
 * 데이터베이스 호출을 한 번만 수행합니다.
 * 모든 데이터를 메모리에 로드한 후 처리하므로, 대규모 데이터셋에서도 빠른 성능을 보일 수 있습니다.
 * 데이터베이스에 종속적이지 않습니다. (PostgreSQL의 WITH RECURSIVE와 같은 특정 기능을 사용하지 않음)
 * 주의사항:
 * 메모리 사용량이 증가할 수 있습니다. 특히 리소스의 수가 매우 많은 경우 주의가 필요합니다.
 * 모든 데이터를 한 번에 가져오므로, 필요하지 않은 데이터도 함께 로드될 수 있습니다.
 * 이 방법은 중간 규모의 데이터셋에서 좋은 성능을 보일 수 있습니다. 하지만 데이터의 규모가 매우 크다면, 페이징 처리나 지연 로딩 등의 추가적인 최적화 기법을 고려해야 할 수 있습니다.
 * 또한, 이 방법을 사용할 때는 Resource 엔티티의 roles 필드를 LAZY 로딩으로 설정하는 것이 좋습니다. 그렇지 않으면 각 Resource에 대해 추가적인 쿼리가 발생할 수 있습니다.
 */
@Repository
@RequiredArgsConstructor
public class ResourceRepositoryAnotherImpl implements ResourceRepositoryCustom {

  private final JPAQueryFactory queryFactory;
  private QResource qResource = QResource.resource;
  private QRole qRole = QRole.role;

  @Override
  public List<Resource> findResourceTreeByUserRoles(List<String> userRoles) {
    // 모든 리소스를 한 번에 가져옵니다.
    List<Resource> allResources = queryFactory.selectDistinct(qResource)
        .from(qResource)
        .join(qResource.roles, qRole)
        .where(qRole.name.in(userRoles))
        .orderBy(qResource.resourceId.asc())
        .fetch();

    // 트리 구조를 구성합니다.
    return buildResourceTree(allResources);
  }

  private List<Resource> buildResourceTree(List<Resource> allResources) {
    Map<String, Resource> resourceMap = new HashMap<>();
    List<Resource> rootResources = new ArrayList<>();

    // 모든 리소스를 Map에 넣습니다.
    for (Resource resource : allResources) {
      resourceMap.put(resource.getResourceId(), resource);
      resource.setChildren(new ArrayList<>()); // 자식 리스트 초기화
    }

    // 부모-자식 관계를 설정합니다.
    for (Resource resource : allResources) {
      if (resource.getParent() == null) {
        rootResources.add(resource);
      } else {
        Resource parent = resourceMap.get(resource.getParent().getResourceId());
        if (parent != null) {
          parent.getChildren().add(resource);
        }
      }
    }

    return rootResources;
  }
}
