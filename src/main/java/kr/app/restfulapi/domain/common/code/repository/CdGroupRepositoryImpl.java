package kr.app.restfulapi.domain.common.code.repository;

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
import kr.app.restfulapi.domain.common.code.entity.CdGroup;
import kr.app.restfulapi.domain.common.code.entity.QCdGroup;
import kr.app.restfulapi.global.util.QuerydslUtils;
import lombok.RequiredArgsConstructor;

/***
 * Querydsl
 */
@Repository
@RequiredArgsConstructor
public class CdGroupRepositoryImpl implements CdGroupRepositoryCustom {

  private final JPAQueryFactory queryFactory;
  private QCdGroup qCdGroup = QCdGroup.cdGroup;

  @Override
  @Transactional(readOnly = true)
  public Page<CdGroup> findAllWithCriteria(CdGroup criteria, Pageable pageable) {

    BooleanExpression whereClause = buildWhereClause(criteria);

    OrderSpecifier<?>[] orderSpecifiers = QuerydslUtils.getSortOrder(new PathBuilder<>(QCdGroup.class, qCdGroup.getMetadata()), pageable.getSort());

    List<CdGroup> results = queryFactory.selectFrom(qCdGroup)
        .where(whereClause)
        .orderBy(orderSpecifiers)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    Long totalCount = queryFactory.select(qCdGroup.count()).from(qCdGroup).where(whereClause).fetchOne();

    return new PageImpl<>(results, pageable, totalCount);
  }

  /**
   * Where절 Build
   * ADMIN권한과 INTERMEDIATE_ADMIN권한이 있으면 조회할 수 있게 분기 처리
   */
  private BooleanExpression buildWhereClause(CdGroup criteria) {

    BooleanExpression whereClause = qCdGroup.isNotNull();

    whereClause = StringUtils.hasText(criteria.getMngSrchDto().cdGroupNm())
        ? whereClause.and(qCdGroup.cdGroupNm.containsIgnoreCase(criteria.getMngSrchDto().cdGroupNm()))
        : whereClause;

    whereClause = StringUtils.hasText(criteria.getMngSrchDto().cdKornNm())
        ? whereClause.and(qCdGroup.cdKornNm.containsIgnoreCase(criteria.getMngSrchDto().cdKornNm()))
        : whereClause;

    whereClause = StringUtils.hasText(criteria.getMngSrchDto().cdSeNm())
        ? whereClause.and(qCdGroup.cdSeNm.containsIgnoreCase(criteria.getMngSrchDto().cdSeNm()))
        : whereClause;

    whereClause = StringUtils.hasText(criteria.getMngSrchDto().cdExpln())
        ? whereClause.and(qCdGroup.cdExpln.containsIgnoreCase(criteria.getMngSrchDto().cdExpln()))
        : whereClause;

    whereClause =
        StringUtils.hasText(criteria.getMngSrchDto().useYn()) ? whereClause.and(qCdGroup.useYn.eq(criteria.getMngSrchDto().useYn())) : whereClause;

    return whereClause;
  }
}
