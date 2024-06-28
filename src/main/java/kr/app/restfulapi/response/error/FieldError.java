package kr.app.restfulapi.response.error;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.validation.BindingResult;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FieldError {

  /** error가 발생한 항목 */
  private String field;

  /** error가 발생한 항목의 값 */
  private String value;

  /** error가 발생한 항목의 이유 */
  private String reason;

  /**
   * @param field error가 발생한 항목
   * @param value error가 발생한 항목의 값
   * @param reason error가 발생한 항목의 이유
   * @return List FieldError
   */
  public static List<FieldError> of(final String field, final String value, final String reason) {
    return List.of(FieldError.builder().field(field).value(value).reason(reason).build());
  }

  /**
   * @param field error가 발생한 항목
   * @param value error가 발생한 항목의 값
   * @param fieldErrorReason field, reason 상수
   * @return List FieldError
   */
  public static List<FieldError> of(String field, String value, FieldErrorReason reason) {
    return List.of(FieldError.builder().field(field).value(value).reason(reason.getReason()).build());
  }

  public static List<FieldError> of(final BindingResult bindingResult) {
    final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
    return fieldErrors.stream()
        .map(error -> FieldError.builder().field(error.getField()).value(Objects.toString(error.getRejectedValue(), "")).reason(error.getDefaultMessage()).build())
        .collect(Collectors.toList());
  }
}
