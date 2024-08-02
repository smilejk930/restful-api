package kr.app.restfulapi.domain.common.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.domain.common.menu.entity.MenuAuthrt;
import kr.app.restfulapi.domain.common.menu.entity.MenuAuthrtId;

@Repository
public interface MenuAuthrtRepository extends JpaRepository<MenuAuthrt, MenuAuthrtId> {

}
