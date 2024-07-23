package kr.app.restfulapi.domain.common.resource.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.domain.common.resource.entity.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, String>, ResourceRepositoryCustom {

  @EntityGraph(attributePaths = {"roles"}) // FetchType.LAZY로 설정된 roles 조회 시 transaction 범위 밖에서 사용하여 LazyInitializationException 방지를 위해 선언
  List<Resource> findAll();

  Optional<Resource> findByUrlPatternAndHttpMethod(String urlPattern, String httpMethod);

  // List<Resource> findByHttpMethodIgnoreCase(String httpMethod);
}
