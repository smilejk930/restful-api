package kr.app.restfulapi.domain.common.menu.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.app.restfulapi.domain.common.menu.entity.Menu;
import kr.app.restfulapi.domain.common.menu.entity.QMenu;
import kr.app.restfulapi.domain.common.menu.entity.QMenuAuthrt;
import kr.app.restfulapi.domain.common.user.gnrl.util.UserType;
import lombok.RequiredArgsConstructor;

/***
 * Querydsl
 */
@Repository
@RequiredArgsConstructor
public class MenuRepositoryImpl implements MenuRepositoryCustom {

  private final JPAQueryFactory queryFactory;
  private QMenu qMenu = QMenu.menu;
  private QMenuAuthrt qMenuAuthrt = QMenuAuthrt.menuAuthrt;

  @Override
  public List<Menu> findMenuTreeByUserTypes(List<UserType> userTypes) {
    JPAQuery<Menu> query = queryFactory.selectDistinct(qMenu)
        .from(qMenu)
        .join(qMenu.menuAuthrts, qMenuAuthrt)
        .where(qMenuAuthrt.userTypeCd.in(userTypes).and(qMenu.parent.isNull()))
        .orderBy(qMenu.menuTsid.asc());

    List<Menu> roots = query.fetch();

    for (Menu root : roots) {
      fetchChildren(root, userTypes);
    }

    return roots;
  }

  private void fetchChildren(Menu parent, List<UserType> userTypes) {
    List<Menu> children = queryFactory.selectDistinct(qMenu)
        .from(qMenu)
        .join(qMenu.menuAuthrts, qMenuAuthrt)
        .where(qMenuAuthrt.userTypeCd.in(userTypes).and(qMenu.parent.eq(parent)))
        .orderBy(qMenu.menuTsid.asc())
        .fetch();

    parent.getChildren().addAll(children);

    for (Menu child : children) {
      fetchChildren(child, userTypes);
    }
  }
}
