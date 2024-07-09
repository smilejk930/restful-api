package kr.app.restfulapi.global.util;

import java.lang.reflect.Field;
import org.apache.commons.lang3.reflect.FieldUtils;
import kr.app.restfulapi.global.response.error.exception.BusinessException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomObjectUtils {

  /** 모든 속성값이 NULL인지 확인 */
  public static boolean isAllNullOfFields(Object object) {
    if (object == null) {
      return true;
    }

    for (Field field : FieldUtils.getAllFields(object.getClass())) {
      try {
        Object fieldValue = FieldUtils.readField(field, object, true);
        if (fieldValue != null) {
          log.error(field.getName());
          return false; // 하나라도 null이 아니면 false 반환
        }
      } catch (IllegalAccessException e) {
        throw new BusinessException(e, "Failed to access field: " + field.getName());
      }
    }

    return true; // 모든 필드가 null일 경우 true 반환
  }

  /** 속성값 중 NULL이 존재하는지 확인 */
  public static boolean isAnyNullOfFields(Object object) {
    if (object == null) {
      return true;
    }

    for (Field field : FieldUtils.getAllFields(object.getClass())) {
      try {
        Object fieldValue = FieldUtils.readField(field, object, true);
        if (fieldValue == null) {
          log.error(field.getName());
          return true; // 하나라도 null이 존재하면 true 반환
        }
      } catch (IllegalAccessException e) {
        throw new BusinessException(e, "Failed to access field: " + field.getName());
      }
    }

    return false; // 모든 필드가 null이 아닐 경우 false 반환
  }
}
