package kr.app.restfulapi.global.auditor;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import kr.app.restfulapi.global.response.error.exception.UnauthorizedException;
import kr.app.restfulapi.global.util.SecurityContextHelper;

/**
 * JPA Auditing을 위한 AuditorAware 구현 클래스입니다.
 * <p>
 * 이 클래스는 현재 인증된 사용자의 정보를 제공하여
 * {@code @CreatedBy}와 {@code @LastModifiedBy} 어노테이션이 지정된 필드에
 * 자동으로 값을 주입하는 데 사용됩니다.
 * </p>
 * <p>
 * SecurityContextHelper를 사용하여 현재 인증된 사용자의 정보를 가져옵니다.
 * </p>
 */
public class AuditorAwareImpl implements AuditorAware<String> {

  /**
   * 현재 인증된 사용자의 식별자를 반환합니다.
   * <p>
   * 이 메서드는 SecurityContextHelper를 사용하여 현재 인증된 사용자의
   * 사용자명을 가져옵니다. 인증된 사용자가 없는 경우 빈 Optional을 반환합니다.
   * </p>
   *
   * @return 현재 인증된 사용자의 사용자명을 포함한 Optional,
   *         인증된 사용자가 없는 경우 빈 Optional
   */
  @Override
  public Optional<String> getCurrentAuditor() {

    try {
      // SecurityContextHelper를 사용하여 현재 인증된 사용자의 사용자아이디를 가져옵니다.
      String userId = SecurityContextHelper.getUserPrincipal().getUserId();
      return Optional.ofNullable(userId);
    } catch (UnauthorizedException e) {
      // 인증되지 않은 경우 예외 처리
      return Optional.empty();
    }
  }

}
