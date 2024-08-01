package kr.app.restfulapi.domain.common.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.domain.common.menu.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String>, MenuRepositoryCustom {

}
