package kr.app.restfulapi.uga.file.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.app.restfulapi.uga.file.entity.FileData;
import kr.app.restfulapi.uga.file.entity.QFileData;
import lombok.RequiredArgsConstructor;

/***
 * Querydsl
 */
@Repository
@RequiredArgsConstructor
public class FileRepositoryImpl implements FileRepositoryCustom {

  private final JPAQueryFactory queryFactory;
  private QFileData qFileData = QFileData.fileData;

  @Override
  @Transactional(readOnly = true)
  public List<FileData> findAllWithCriteria(FileData criteria) {

    BooleanExpression whereClause = buildWhereClause(criteria);

    JPAQuery<FileData> query = queryFactory.selectFrom(qFileData).where(whereClause);

    // ORDER BY 절을 설정
    OrderSpecifier<String> orderByFileSectValue = qFileData.fileSectValue.asc();
    OrderSpecifier<Long> orderByFileSn = qFileData.fileSn.asc();
    query.orderBy(orderByFileSectValue, orderByFileSn);

    return query.fetch();
  }

  /**
   * Where절 Build
   */
  private BooleanExpression buildWhereClause(FileData criteria) {

    return qFileData.deleteAt.eq("N").and(qFileData.fileGroupNm.eq(criteria.getFileGroupNm())).and(qFileData.refrnId.eq(criteria.getRefrnId()));
  }
}
