package kr.app.restfulapi.global.auditor;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import kr.app.restfulapi.global.response.error.exception.UnauthorizedException;
import kr.app.restfulapi.global.util.SecurityContextHelper;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
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
      // SecurityContextHelper를 사용하여 현재 인증된 사용자의 사용자식별번호를 가져옵니다.
      String userTsid = SecurityContextHelper.getUserPrincipal().getUserTsid();
      return Optional.ofNullable(userTsid);
    } catch (UnauthorizedException e) {
      log.warn("사용자 인증 처리가 되지 않아서 예외처리 발생");
      log.warn("- 비로그인 사용자가 등록 또는 수정 처리하는 경우(사용자가입)");
      log.warn("- 시스템에서 자동으로 처리할 경우");
      return Optional.empty();
    }
  }

}
