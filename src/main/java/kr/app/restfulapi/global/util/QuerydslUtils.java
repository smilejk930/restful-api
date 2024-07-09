package kr.app.restfulapi.global.util;

import org.springframework.data.domain.Sort;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import kr.app.restfulapi.global.response.error.exception.IllegalArgumentException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuerydslUtils {

  /** sort order 정렬 조건 */
  public static OrderSpecifier<?>[] getSortOrder(PathBuilder<?> entityPath, Sort sort) {
    return sort.stream().map(order -> {
      switch (order.getDirection()) {
        case ASC:
          return entityPath.getString(order.getProperty()).asc();
        case DESC:
          return entityPath.getString(order.getProperty()).desc();
        default:
          String message = "Unsupported sort direction: " + order.getDirection();
          log.error(message);
          throw new IllegalArgumentException();
      }
    }).toArray(OrderSpecifier[]::new);
  }
}
