package kr.app.restfulapi.domain.common.menu.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.domain.common.menu.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String>, MenuRepositoryCustom {

  @EntityGraph(attributePaths = {"menuAuthrts"}) // FetchType.LAZY로 설정된 menuAuthrts 조회 시 transaction 범위 밖에서 사용(DynamicAuthorizationFilter)하여
                                                 // LazyInitializationException 방지를 위해 선언
  List<Menu> findAll();

  List<Menu> findAllByMenuGroupCd(String menuGroupCd, Sort sort);

  Optional<Menu> findByMenuTsid(String menuTsid);

  Optional<Menu> findByHttpDmndMethNmAndUrlAddr(String httpDmndMethNm, String urlAddr);
}
