package kr.app.restfulapi.uga.post.repository;

//Q클래스 static import
import static kr.app.restfulapi.uga.post.entity.QPost.post;
import java.util.List;
import java.util.Optional;
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
import kr.app.restfulapi.uga.post.entity.Post;
import kr.app.restfulapi.uga.post.entity.QPost;
import kr.app.restfulapi.util.QuerydslUtil;
import lombok.RequiredArgsConstructor;

/***
 * Querydsl
 */
@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  @Transactional(readOnly = true)
  public Page<Post> findAllWithCriteria(Post condition, Pageable pageable) {

    BooleanExpression predicate = buildPredicate(condition);

    OrderSpecifier<?>[] orderSpecifiers = QuerydslUtil.getSortOrder(new PathBuilder<>(QPost.class, post.getMetadata()), pageable.getSort());

    List<Post> results =
        queryFactory.selectFrom(post).where(predicate).orderBy(orderSpecifiers).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();

    Long totalCount = queryFactory.select(post.count()).from(post).where(predicate).fetchOne();

    return new PageImpl<>(results, pageable, totalCount);
  }

  /**
   * 조건절 Build
   */
  private BooleanExpression buildPredicate(Post condition) {

    BooleanExpression predicate = post.isNotNull();

    Optional<Post> opt = Optional.ofNullable(condition);
    opt.map(Post::getSj).filter(StringUtils::hasText).ifPresent(str -> predicate.and(post.sj.containsIgnoreCase(str)));
    opt.map(Post::getCn).filter(StringUtils::hasText).ifPresent(str -> predicate.and(post.cn.containsIgnoreCase(str)));

    return predicate;
  }
}
