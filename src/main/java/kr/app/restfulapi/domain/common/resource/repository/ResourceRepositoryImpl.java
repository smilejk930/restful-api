package kr.app.restfulapi.domain.common.resource.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.app.restfulapi.domain.common.resource.entity.QResource;
import kr.app.restfulapi.domain.common.resource.entity.Resource;
import kr.app.restfulapi.domain.common.role.entity.QRole;
import lombok.RequiredArgsConstructor;

/**
 * 재귀쿼리 방식
 */
@Repository
@RequiredArgsConstructor
public class ResourceRepositoryImpl implements ResourceRepositoryCustom {

  private final JPAQueryFactory queryFactory;
  private QResource qResource = QResource.resource;
  private QRole qRole = QRole.role;

  @Override
  public List<Resource> findResourceTreeByUserRoles(List<String> userRoles) {
    JPAQuery<Resource> query = queryFactory.selectDistinct(qResource)
        .from(qResource)
        .join(qResource.roles, qRole)
        .where(qRole.name.in(userRoles).and(qResource.parent.isNull()))
        .orderBy(qResource.resourceId.asc());

    List<Resource> roots = query.fetch();

    for (Resource root : roots) {
      fetchChildren(root, userRoles);
    }

    return roots;
  }

  private void fetchChildren(Resource parent, List<String> userRoles) {
    List<Resource> children = queryFactory.selectDistinct(qResource)
        .from(qResource)
        .join(qResource.roles, qRole)
        .where(qRole.name.in(userRoles).and(qResource.parent.eq(parent)))
        .orderBy(qResource.resourceId.asc())
        .fetch();

    parent.getChildren().addAll(children);

    for (Resource child : children) {
      fetchChildren(child, userRoles);
    }
  }
}
