package kr.app.restfulapi.global.filter;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.app.restfulapi.domain.common.menu.entity.Menu;
import kr.app.restfulapi.domain.common.menu.entity.MenuAuthrt;
import kr.app.restfulapi.domain.common.menu.service.MenuService;
import kr.app.restfulapi.domain.common.menu.util.MenuAcsAuthrtType;
import kr.app.restfulapi.domain.common.user.gnrl.util.UserPrincipal;
import kr.app.restfulapi.domain.common.user.gnrl.util.UserType;
import kr.app.restfulapi.global.response.error.exception.BusinessException;
import kr.app.restfulapi.global.response.error.exception.ForbiddenException;
import kr.app.restfulapi.global.response.error.exception.ResourceNotFoundException;
import kr.app.restfulapi.global.response.error.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DynamicAuthorizationFilter extends OncePerRequestFilter {

  private final MenuService menuService;
  private final AntPathMatcher antPathMatcher = new AntPathMatcher();

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String lgnId = null;
    String url = request.getRequestURI();
    String method = request.getMethod();

    try {
      // TODO URL 사용여부 추가
      List<Menu> menus = menuService.getAllMenu();

      Menu matchedMenu = menus.stream()
          .filter(menu -> antPathMatcher.match(menu.getUrlAddr(), url) && menu.getHttpDmndMethNm().equalsIgnoreCase(method))
          .findFirst()
          .orElseThrow(() -> new ResourceNotFoundException("No matching resource found for URL: " + url + " and method: " + method));

      if (matchedMenu.getMenuAcsAuthrtCd() == MenuAcsAuthrtType.MAA003) {
        log.debug("비로그인 사용자 접근\n-url:{}\n-method:{}", url, method);
        filterChain.doFilter(request, response);
        return;
      }

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      // 로그인 사용자 확인
      if (authentication == null || !authentication.isAuthenticated()) {
        throw new UnauthorizedException();
      }

      UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
      lgnId = userPrincipal.getLgnId();

      Set<MenuAuthrt> menuAuthrts = matchedMenu.getMenuAuthrts(); // FetchType.LAZY로 설정된 menuAuthrts 조회

      // matchedMenu.getMenuAcsAuthrtCd() == MenuAcsAuthrtType.MAA002
      if (menuAuthrts == null || menuAuthrts.isEmpty()) {
        // 메뉴에 역할이 지정되지 않은 경우의 처리
        filterChain.doFilter(request, response);
        return;
      }

      boolean hasPermission = menuAuthrts.stream()
          .anyMatch(menuAuthrt -> authentication.getAuthorities()
              .stream()
              .anyMatch(authority -> authority.getAuthority().equals(menuAuthrt.getUserTypeCd().name())));

      boolean isAdmin = userPrincipal.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals(UserType.USR000.name()));

      /** 관리자권한을 가지고 있으면 권한체크 안함 */
      if (!isAdmin && !hasPermission) {
        throw new ForbiddenException("Access Denied for user: " + lgnId);
      }

      filterChain.doFilter(request, response);
    } catch (ResourceNotFoundException | UnauthorizedException | ForbiddenException ex) {
      log.error("Authorization error for user: " + lgnId, ex);
      throw ex; // Re-throw to be handled by GlobalExceptionHandler
    } catch (Exception ex) {
      log.error("Unexpected error occurred during dynamic authorization for user: " + lgnId, ex);
      throw new BusinessException(ex, "Internal Server Error");
    }
  }

}
