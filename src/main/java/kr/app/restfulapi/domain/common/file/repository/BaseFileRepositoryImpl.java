package kr.app.restfulapi.domain.common.file.repository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.app.restfulapi.domain.common.file.entity.BaseFileEntity;
import kr.app.restfulapi.domain.common.file.util.FileGroupNmType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BaseFileRepositoryImpl<T extends BaseFileEntity> implements BaseFileRepositoryCustom<T> {

  private final JPAQueryFactory queryFactory;
  private final EntityPathBase<T> qFileEntity;

  @Override
  @Transactional(readOnly = true)
  public List<T> findAllWithCriteria(T criteria) {

    BooleanExpression whereClause = buildWhereClause(criteria);

    JPAQuery<T> query = queryFactory.selectFrom(qFileEntity).where(whereClause);

    // ORDER BY 절을 설정
    OrderSpecifier<String> orderByFileClsfNm = new OrderSpecifier<>(Order.ASC, Expressions.stringPath(qFileEntity, "fileClsfNm"));
    OrderSpecifier<Long> orderByFileSeq = new OrderSpecifier<>(Order.ASC, Expressions.numberPath(Long.class, qFileEntity, "fileSeq"));

    query.orderBy(orderByFileClsfNm, orderByFileSeq);

    return query.fetch();
  }

  public Long findByMaxFileSeq(T criteria) {
    BooleanExpression whereClause = buildWhereClause(criteria);
    whereClause = whereClause.and(Expressions.stringPath(qFileEntity, "fileClsfNm").eq(criteria.getFileClsfNm()));

    JPAQuery<Long> query = queryFactory.select(Expressions.numberPath(Long.class, qFileEntity, "fileSeq").max()).from(qFileEntity).where(whereClause);

    Long maxFileSeq = query.fetchOne();

    return maxFileSeq != null ? maxFileSeq + 1 : 1L;
  }

  /**
   * Where절 Build
   */
  private BooleanExpression buildWhereClause(T criteria) {
    return Expressions.stringPath(qFileEntity, "delYn")
        .eq("N")
        .and(Expressions.enumPath(FileGroupNmType.class, qFileEntity, "fileGroupNm").eq(criteria.getFileGroupNm()))
        .and(Expressions.stringPath(qFileEntity, "rfrncTsid").eq(criteria.getRfrncTsid()));
  }
}
