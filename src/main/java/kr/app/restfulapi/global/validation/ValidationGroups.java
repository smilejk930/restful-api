package kr.app.restfulapi.global.validation;

/**
 * Bean Validation 어노테이션과 함께 사용할 검증 그룹을 정의합니다.
 * <p>
 * 이 클래스는 서로 다른 검증 시나리오를 위한 마커 인터페이스를 포함합니다.
 * 이 그룹들은 {@code @Validated} 어노테이션이나 검증 제약 어노테이션에서
 * 특정 검증이 적용되어야 할 시점을 지정하는 데 사용될 수 있습니다.
 * </p>
 */
public class ValidationGroups {

  /**
   * 임시 저장 작업을 위한 검증 그룹입니다.
   * <p>
   * 모든 필드가 필수가 아니거나 완전히 검증될 필요가 없는
   * 임시 저장 시나리오에서 데이터를 검증할 때 이 그룹을 사용합니다.
   * </p>
   */
  // public interface TempCreate {}

  /**
   * 최종 제출 작업을 위한 검증 그룹입니다.
   * <p>
   * 모든 필수 필드가 존재하고 완전히 검증되어야 하는
   * 최종 제출 시나리오에서 데이터를 검증할 때 이 그룹을 사용합니다.
   * </p>
   */
  public interface FinalSubmit {
  }
}
