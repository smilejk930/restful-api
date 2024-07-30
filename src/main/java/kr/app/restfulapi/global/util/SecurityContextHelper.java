package kr.app.restfulapi.global.util;

import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import kr.app.restfulapi.domain.common.role.util.RoleName;
import kr.app.restfulapi.domain.common.user.gnrl.util.UserPrincipal;
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

  /**
   * 현재 인증된 사용자가 주어진 역할을 가지고 있는지 확인합니다.
   * 
   * @param roles 확인할 역할 목록
   * @return 주어진 역할 중 하나라도 가지고 있으면 true, 그렇지 않으면 false
   */
  public static boolean hasAnyRole(RoleName... roles) {
    UserPrincipal userPrincipal = getUserPrincipal();
    List<String> authorities = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

    for (RoleName role : roles) {
      if (authorities.contains(role.name())) {
        return true;
      }
    }
    return false;
  }

  /**
   * 주어진 역할 그룹들 중 하나라도 현재 인증된 사용자가 가지고 있는지 확인합니다.
   * 
   * @param roleGroup 확인할 역할 그룹 배열
   * @return 주어진 역할 그룹들 중 하나라도 가지고 있으면 true, 그렇지 않으면 false
   */
  public static boolean hasAnyRoleFromGroups(RoleName[]... roleGroup) {
    for (RoleName[] roles : roleGroup) {
      if (hasAnyRole(roles)) {
        return true;
      }
    }
    return false;
  }
}
