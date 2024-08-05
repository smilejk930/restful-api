package kr.app.restfulapi.domain.common.menu.repository;

import java.util.List;
import kr.app.restfulapi.domain.common.menu.entity.Menu;
import kr.app.restfulapi.domain.common.user.gnrl.util.UserType;

public interface MenuRepositoryCustom {

  List<Menu> findMenuTreeByUserTypes(List<UserType> userTypes);
}
