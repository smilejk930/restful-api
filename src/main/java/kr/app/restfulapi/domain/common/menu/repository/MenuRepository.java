package kr.app.restfulapi.domain.common.menu.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.domain.common.menu.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String>, MenuRepositoryCustom {

  List<Menu> findAllByMenuGroupCd(String menuGroupCd, Sort sort);

  Optional<Menu> findByMenuTsid(String menuTsid);

  Optional<Menu> findByHttpDmndMethNmAndUrlAddr(String httpDmndMethNm, String urlAddr);
}
