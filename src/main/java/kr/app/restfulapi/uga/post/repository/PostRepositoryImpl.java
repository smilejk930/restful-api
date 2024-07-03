package kr.app.restfulapi.uga.post.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.app.restfulapi.uga.common.util.QuerydslUtils;
import kr.app.restfulapi.uga.post.entity.Post;
import kr.app.restfulapi.uga.post.entity.QPost;
import lombok.RequiredArgsConstructor;

/***
 * Querydsl
 */
@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

  private final JPAQueryFactory queryFactory;
  private QPost qPost = QPost.post;

  @Override
  @Transactional(readOnly = true)
  public Page<Post> findAllWithCriteria(Post criteria, Pageable pageable) {

    BooleanExpression whereClause = buildWhereClause(criteria);

    OrderSpecifier<?>[] orderSpecifiers = QuerydslUtils.getSortOrder(new PathBuilder<>(QPost.class, qPost.getMetadata()), pageable.getSort());

    List<Post> results =
        queryFactory.selectFrom(qPost).where(whereClause).orderBy(orderSpecifiers).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();

    Long totalCount = queryFactory.select(qPost.count()).from(qPost).where(whereClause).fetchOne();

    return new PageImpl<>(results, pageable, totalCount);
  }

  /**
   * Where절 Build
   */
  private BooleanExpression buildWhereClause(Post criteria) {

    BooleanExpression whereClause = qPost.isNotNull();

    whereClause = StringUtils.hasText(criteria.getSj()) ? whereClause.and(qPost.sj.containsIgnoreCase(criteria.getSj())) : whereClause;
    whereClause = StringUtils.hasText(criteria.getCn()) ? whereClause.and(qPost.cn.containsIgnoreCase(criteria.getCn())) : whereClause;

    return whereClause;
  }

}
