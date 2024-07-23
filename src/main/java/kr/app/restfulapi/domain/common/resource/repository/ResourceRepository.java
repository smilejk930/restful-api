package kr.app.restfulapi.domain.common.resource.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.domain.common.resource.entity.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, String>, ResourceRepositoryCustom {

  Optional<Resource> findByUrlPatternAndHttpMethod(String urlPattern, String httpMethod);

  // List<Resource> findByHttpMethodIgnoreCase(String httpMethod);
}
