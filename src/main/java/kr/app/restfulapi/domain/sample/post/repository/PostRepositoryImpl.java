package kr.app.restfulapi.domain.sample.post.repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.app.restfulapi.domain.common.role.util.RoleGroup;
import kr.app.restfulapi.domain.common.user.gnrl.entity.QGnrlUser;
import kr.app.restfulapi.domain.common.user.gnrl.util.UserPrincipal;
import kr.app.restfulapi.domain.sample.post.entity.Post;
import kr.app.restfulapi.domain.sample.post.entity.QPost;
import kr.app.restfulapi.global.util.QuerydslUtils;
import kr.app.restfulapi.global.util.SecurityContextHelper;
import lombok.RequiredArgsConstructor;

/***
 * Querydsl
 */
@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

  private final JPAQueryFactory queryFactory;
  private QPost qPost = QPost.post;
  private QGnrlUser qGnrlUser = QGnrlUser.gnrlUser;

  @Override
  @Transactional(readOnly = true)
  public Page<Post> findAllWithCriteria(Post criteria, Pageable pageable) {

    BooleanExpression whereClause = buildWhereClause(criteria);

    OrderSpecifier<?>[] orderSpecifiers = QuerydslUtils.getSortOrder(new PathBuilder<>(QPost.class, qPost.getMetadata()), pageable.getSort());

    /*
    List<Post> results =
        queryFactory.selectFrom(qPost).where(whereClause).orderBy(orderSpecifiers).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        
    Long totalCount = queryFactory.select(qPost.count()).from(qPost).where(whereClause).fetchOne();
    */
    List<Tuple> tupleResults = queryFactory.select(qPost, qGnrlUser.userNm)
        .from(qPost)
        .leftJoin(qGnrlUser)
        .on(qPost.rgtrTsid.eq(qGnrlUser.userTsid))
        .where(whereClause)
        .orderBy(orderSpecifiers)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    List<Post> results = tupleResults.stream().map(tuple -> {
      Post post = tuple.get(qPost);
      post.setUserNm(tuple.get(qGnrlUser.userNm));
      return post;
    }).toList();

    Long totalCount =
        queryFactory.select(qPost.count()).from(qPost).leftJoin(qGnrlUser).on(qPost.rgtrTsid.eq(qGnrlUser.userTsid)).where(whereClause).fetchOne();

    return new PageImpl<>(results, pageable, totalCount);
  }

  /**
   * Where절 Build
   * ADMIN권한과 INTERMEDIATE_ADMIN권한이 있으면 조회할 수 있게 분기 처리
   */
  private BooleanExpression buildWhereClause(Post criteria) {

    UserPrincipal userPrincipal = SecurityContextHelper.getUserPrincipal();

    BooleanExpression whereClause = qPost.isNotNull();

    whereClause = whereClause.and(qPost.delYn.eq("N"));

    // RoleName.ADMIN와 RoleName.INTERMEDIATE_ADMIN 권한을 가지고 있는지 확인
    if (!SecurityContextHelper.hasAnyRole(RoleGroup.ADMIN_GROUP)) {
      whereClause = whereClause.and(qPost.rgtrTsid.eq(userPrincipal.getUserTsid()));
    }
    whereClause =
        StringUtils.hasText(criteria.getSrchDto().ttl()) ? whereClause.and(qPost.ttl.containsIgnoreCase(criteria.getSrchDto().ttl())) : whereClause;
    whereClause =
        StringUtils.hasText(criteria.getSrchDto().cn()) ? whereClause.and(qPost.cn.containsIgnoreCase(criteria.getSrchDto().cn())) : whereClause;
    whereClause =
        StringUtils.hasText(criteria.getSrchDto().userNm()) ? whereClause.and(qGnrlUser.userNm.containsIgnoreCase(criteria.getSrchDto().userNm()))
            : whereClause;

    if (criteria.getSrchDto().pstgBgngYmd() != null) {
      whereClause = whereClause.and(qPost.regDt.goe(criteria.getSrchDto().pstgBgngYmd().atStartOfDay()));
    }
    if (criteria.getSrchDto().pstgEndYmd() != null) {
      whereClause = whereClause.and(qPost.regDt.loe(criteria.getSrchDto().pstgEndYmd().atTime(LocalTime.MAX)));
    }

    return whereClause;
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Post> findByPostTsid(String postTsid) {

    UserPrincipal userPrincipal = SecurityContextHelper.getUserPrincipal();

    BooleanExpression whereClause = qPost.isNotNull();

    whereClause = whereClause.and(qPost.delYn.eq("N"));
    whereClause = whereClause.and(qPost.postTsid.eq(postTsid));

    // RoleName.ADMIN와 RoleName.INTERMEDIATE_ADMIN 권한을 가지고 있는지 확인
    if (!SecurityContextHelper.hasAnyRole(RoleGroup.ADMIN_GROUP)) {
      whereClause = whereClause.and(qPost.rgtrTsid.eq(userPrincipal.getUserTsid()));
    }

    // Post result = queryFactory.selectFrom(qPost).where(whereClause).fetchOne();

    return queryFactory.select(qPost, qGnrlUser.userNm)
        .from(qPost)
        .leftJoin(qGnrlUser)
        .on(qPost.rgtrTsid.eq(qGnrlUser.userTsid))
        .where(whereClause)
        .fetch()
        .stream()
        .map(tuple -> {
          Post post = tuple.get(qPost);
          post.setUserNm(tuple.get(qGnrlUser.userNm));
          return post;
        })
        .findFirst();// Optional<Post>를 반환
  }

}
