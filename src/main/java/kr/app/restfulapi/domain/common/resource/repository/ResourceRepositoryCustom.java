package kr.app.restfulapi.domain.common.resource.repository;

import java.util.List;
import kr.app.restfulapi.domain.common.resource.entity.Resource;

public interface ResourceRepositoryCustom {

  List<Resource> findResourceTreeByUserRoles(List<String> userRoles);
}
