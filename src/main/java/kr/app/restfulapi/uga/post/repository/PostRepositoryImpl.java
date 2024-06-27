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
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.app.restfulapi.uga.post.entity.Post;
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

    BooleanExpression predicate = buildPredicate(Optional.ofNullable(condition));

    List<Post> results =
        queryFactory.selectFrom(post).where(predicate).orderBy(post.registDt.desc())
            .offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();

    Long totalCount = queryFactory.select(post.count()).from(post).where(predicate).fetchOne();

    return new PageImpl<>(results, pageable, totalCount);
  }

  private BooleanExpression buildPredicate(Optional<Post> optPost) {
    String sj = optPost.map(Post::getSj).orElse("");
    String cn = optPost.map(Post::getCn).orElse("");

    BooleanExpression predicate = post.isNotNull();

    predicate = StringUtils.hasText(sj) ? predicate.and(post.sj.containsIgnoreCase(sj)) : predicate;
    predicate = StringUtils.hasText(cn) ? predicate.and(post.cn.containsIgnoreCase(cn)) : predicate;

    return predicate;
  }
}
