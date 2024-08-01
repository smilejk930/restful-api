package kr.app.restfulapi.global.filter;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.app.restfulapi.domain.common.resource.entity.Resource;
import kr.app.restfulapi.domain.common.resource.service.ResourceService;
import kr.app.restfulapi.domain.common.resource.util.ResourceAccessType;
import kr.app.restfulapi.domain.common.role.entity.Role;
import kr.app.restfulapi.domain.common.user.gnrl.util.UserPrincipal;
import kr.app.restfulapi.global.response.error.exception.BusinessException;
import kr.app.restfulapi.global.response.error.exception.ForbiddenException;
import kr.app.restfulapi.global.response.error.exception.ResourceNotFoundException;
import kr.app.restfulapi.global.response.error.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DynamicAuthorizationFilter extends OncePerRequestFilter {

  private final ResourceService resourceService;
  private final AntPathMatcher antPathMatcher = new AntPathMatcher();

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String lgnId = null;
    String url = request.getRequestURI();
    String method = request.getMethod();

    try {
      // TODO URL 사용여부 추가
      List<Resource> resources = resourceService.getAllResource();

      Resource matchedResource = resources.stream()
          .filter(resource -> antPathMatcher.match(resource.getUrlPattern(), url) && resource.getHttpMethod().equalsIgnoreCase(method))
          .findFirst()
          .orElseThrow(() -> new ResourceNotFoundException("No matching resource found for URL: " + url + " and method: " + method));

      if (matchedResource.getResourceAccessType().equals(ResourceAccessType.NON_MEMBERS)) {
        log.debug("비로그인 사용자 접근\n-url:{}\n-method:{}", url, method);
        filterChain.doFilter(request, response);
        return;
      }

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      if (authentication == null || !authentication.isAuthenticated()) {
        throw new UnauthorizedException();
      }

      UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
      lgnId = userPrincipal.getLgnId();

      Set<Role> resourceRoles = matchedResource.getRoles();

      if (resourceRoles == null || resourceRoles.isEmpty()) {
        // 리소스에 역할이 지정되지 않은 경우의 처리
        filterChain.doFilter(request, response);
        return;
      }

      boolean hasPermission = resourceRoles.stream()
          .anyMatch(role -> authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals(role.getName())));

      if (!hasPermission) {
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
