package kr.app.restfulapi.global.filter;

import java.io.IOException;
import java.util.Optional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.app.restfulapi.domain.common.user.service.CustomUserDetailsService;
import kr.app.restfulapi.global.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider tokenProvider;
  private final CustomUserDetailsService customUserDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String jwt = getJwtFromRequest(request);

      if (StringUtils.hasText(jwt)) {
        if (tokenProvider.isValidateToken(jwt)) {
          Optional<String> optLoginId = tokenProvider.getLoginIdFromJWT(jwt);
          if (optLoginId.isPresent()) {
            UserDetails userDetails = customUserDetailsService.loadUserByLoginId(optLoginId.get());

            if (userDetails != null) {
              // TODO 어떤 역할을 하는지 확인해야함
              UsernamePasswordAuthenticationToken authentication =
                  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
              authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

              SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
              // 유효한 사용자가 아닌 경우 로그를 남기고 인증을 설정하지 않습니다.
              logger.warn("User not found for Login Id: " + optLoginId.get());
              SecurityContextHolder.clearContext();// 보안 컨텍스트를 클리어합니다.
            }

          } else {
            logger.error("Could not find loginId in JWT token");
            SecurityContextHolder.clearContext();
          }
        } else {
          logger.warn("Invalid JWT token");
        }
      }
    } catch (UsernameNotFoundException ex) {
      logger.error("User not found in database", ex);
      SecurityContextHolder.clearContext();
    } catch (Exception ex) {
      logger.error("Could not set user authentication in security context", ex);
      SecurityContextHolder.clearContext();
    }

    filterChain.doFilter(request, response);
  }

  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

}
