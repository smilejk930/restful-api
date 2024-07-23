package kr.app.restfulapi.global.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import kr.app.restfulapi.domain.common.user.util.UserPrincipal;
import kr.app.restfulapi.global.response.error.exception.UnauthorizedException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 보안 컨텍스트에서 사용자 정보를 추출하는 유틸리티 클래스입니다.
 * <p>
 * 이 클래스는 Spring Security의 SecurityContextHolder를 사용하여
 * 현재 인증된 사용자의 정보를 안전하게 가져오는 메서드를 제공합니다.
 * </p>
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SecurityContextHelper {

  /**
   * 현재 인증된 사용자의 UserPrincipal 객체를 반환합니다.
   * 
   * @return 현재 인증된 사용자의 UserPrincipal 객체
   * @throws UnauthorizedException 사용자가 인증되지 않았거나 사용자 정보를 찾을 수 없는 경우
   */
  public static UserPrincipal getUserPrincipal() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      throw new UnauthorizedException();
    }

    Object principal = authentication.getPrincipal();
    if (principal instanceof UserPrincipal userPrincipal) {
      return userPrincipal;
    }

    throw new UnauthorizedException("사용자 정보를 찾을 수 없습니다.");
  }
}
