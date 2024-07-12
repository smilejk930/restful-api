package kr.app.restfulapi.global.filter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
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
import kr.app.restfulapi.domain.common.resource.repository.ResourceRepository;
import kr.app.restfulapi.domain.common.role.entity.Role;
import kr.app.restfulapi.domain.common.user.util.UserPrincipal;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DynamicAuthorizationFilter extends OncePerRequestFilter {

  private final ResourceRepository resourceRepository;
  private final AntPathMatcher antPathMatcher = new AntPathMatcher();

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String url = request.getRequestURI();
    String method = request.getMethod();

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !authentication.isAuthenticated()) {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not authenticated");
      return;
    }

    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
    String loginId = userPrincipal.getLoginId();

    try {
      // TODO URL 사용여부 추가
      List<Resource> resources = resourceRepository.findAll();

      Optional<Resource> matchedResource = resources.stream()
          .filter(resource -> antPathMatcher.match(resource.getUrlPattern(), url) && resource.getMethod().equalsIgnoreCase(method))
          .findFirst();

      if (matchedResource.isPresent()) {

        Set<Role> resourceRoles = matchedResource.get().getRoles();

        if (resourceRoles == null || resourceRoles.isEmpty()) {
          // 리소스에 역할이 지정되지 않은 경우의 처리
          filterChain.doFilter(request, response);
          return;
        }

        boolean hasPermission = resourceRoles.stream()
            .anyMatch(role -> authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals(role.getName())));

        if (!hasPermission) {
          response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
          return;
        }
      }

      filterChain.doFilter(request, response);
    } catch (Exception ex) {
      logger.error("Error occurred during dynamic authorization for user: " + loginId, ex);
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
    }
  }

}
