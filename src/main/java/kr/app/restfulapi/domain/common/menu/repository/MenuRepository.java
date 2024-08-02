package kr.app.restfulapi.domain.common.menu.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.domain.common.menu.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String>, MenuRepositoryCustom {

  Optional<Menu> findByHttpDmndMethNmAndUrlAddr(String httpDmndMethNm, String urlAddr);
}
