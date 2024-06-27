package kr.app.restfulapi.util;

import org.springframework.data.domain.Sort;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;

public class QuerydslUtil {

  /** sort order 정렬 조건 */
  public static OrderSpecifier<?>[] getSortOrder(PathBuilder<?> entityPath, Sort sort) {
    return sort.stream().map(order -> {
      switch (order.getDirection()) {
        case ASC:
          return entityPath.getString(order.getProperty()).asc();
        case DESC:
          return entityPath.getString(order.getProperty()).desc();
        default:
          throw new IllegalArgumentException("Unsupported sort direction: " + order.getDirection());
      }
    }).toArray(OrderSpecifier[]::new);
  }
}
