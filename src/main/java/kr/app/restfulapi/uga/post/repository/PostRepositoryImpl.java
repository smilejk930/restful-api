package kr.app.restfulapi.uga.post.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.app.restfulapi.uga.post.entity.Post;
import kr.app.restfulapi.uga.post.entity.QPost;
import lombok.RequiredArgsConstructor;

//Querydsl
@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  @Transactional(readOnly = true)
  public Page<Post> findAllWithCriteria(Post post, Pageable pageable) {
    QPost qPost = QPost.post;

    // Apply search criteria if provided
    BooleanBuilder whereBuilder = new BooleanBuilder();
    if (post != null) {
      if (post.getSj() != null) {
        whereBuilder.and(qPost.sj.containsIgnoreCase(post.getSj()));
      }
      if (post.getCn() != null) {
        whereBuilder.and(qPost.cn.containsIgnoreCase(post.getCn()));
      }
    }
    List<Post> results =
        queryFactory.selectFrom(qPost).where(whereBuilder).orderBy(qPost.registDt.desc())
            .offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
    long totalCount = results.size();

    return new PageImpl<>(results, pageable, totalCount);
  }
}
