package kr.app.restfulapi.domain.common.code.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.app.restfulapi.domain.common.code.entity.Cd;
import kr.app.restfulapi.domain.common.code.entity.QCd;
import lombok.RequiredArgsConstructor;

/***
 * Querydsl
 */
@Repository
@RequiredArgsConstructor
public class CdRepositoryImpl implements CdRepositoryCustom {

  private final JPAQueryFactory queryFactory;
  private QCd qCd = QCd.cd;

  @Override
  @Transactional(readOnly = true)
  public List<Cd> findAllWithCriteria(String cdGroupNm, Cd criteria) {

    criteria.setCdGroupNm(cdGroupNm);
    BooleanExpression whereClause = buildWhereClause(criteria);

    return queryFactory.selectFrom(qCd).where(whereClause).orderBy(qCd.cdGroup.cdGroupNm.asc(), qCd.cdSeq.asc()).fetch();
  }

  /**
   * Where절 Build
   * ADMIN권한과 INTERMEDIATE_ADMIN권한이 있으면 조회할 수 있게 분기 처리
   */
  private BooleanExpression buildWhereClause(Cd criteria) {

    BooleanExpression whereClause = qCd.isNotNull();

    whereClause = whereClause.and(qCd.cdGroup.cdGroupNm.eq(criteria.getCdGroupNm()));

    whereClause = StringUtils.hasText(criteria.getMngSrchDto().cdNm()) ? whereClause.and(qCd.cdNm.containsIgnoreCase(criteria.getMngSrchDto().cdNm()))
        : whereClause;

    whereClause = StringUtils.hasText(criteria.getMngSrchDto().cdKornNm())
        ? whereClause.and(qCd.cdKornNm.containsIgnoreCase(criteria.getMngSrchDto().cdKornNm()))
        : whereClause;

    whereClause =
        StringUtils.hasText(criteria.getMngSrchDto().upCdNm()) ? whereClause.and(qCd.upCdNm.containsIgnoreCase(criteria.getMngSrchDto().upCdNm()))
            : whereClause;

    whereClause =
        StringUtils.hasText(criteria.getMngSrchDto().cdExpln()) ? whereClause.and(qCd.cdExpln.containsIgnoreCase(criteria.getMngSrchDto().cdExpln()))
            : whereClause;

    whereClause =
        StringUtils.hasText(criteria.getMngSrchDto().useYn()) ? whereClause.and(qCd.useYn.eq(criteria.getMngSrchDto().useYn())) : whereClause;

    return whereClause;
  }
}
