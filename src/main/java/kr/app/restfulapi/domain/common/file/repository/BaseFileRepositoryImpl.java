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
    OrderSpecifier<String> orderByFileSectValue = new OrderSpecifier<>(Order.ASC, Expressions.stringPath(qFileEntity, "fileSectValue"));
    OrderSpecifier<Long> orderByFileSn = new OrderSpecifier<>(Order.ASC, Expressions.numberPath(Long.class, qFileEntity, "fileSn"));

    query.orderBy(orderByFileSectValue, orderByFileSn);

    return query.fetch();
  }

  /**
   * Where절 Build
   */
  private BooleanExpression buildWhereClause(T criteria) {
    return Expressions.stringPath(qFileEntity, "delYn")
        .eq("N")
        .and(Expressions.stringPath(qFileEntity, "fileGroupNm").eq(criteria.getFileGroupNm()))
        .and(Expressions.stringPath(qFileEntity, "refrnId").eq(criteria.getRefrnId()));
  }
}
