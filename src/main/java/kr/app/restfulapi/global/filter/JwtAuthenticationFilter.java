package kr.app.restfulapi.global.filter;

import java.io.IOException;
import java.util.Optional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.app.restfulapi.domain.common.user.gnrl.service.CustomUserDetailsService;
import kr.app.restfulapi.global.response.error.exception.JwtAuthenticationException;
import kr.app.restfulapi.global.response.error.exception.JwtTokenException;
import kr.app.restfulapi.global.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

        if (!tokenProvider.isValidateToken(jwt)) {
          throw new JwtTokenException("Invalid JWT token");
        }

        Optional<String> optLgnId = tokenProvider.getLgnIdFromJWT(jwt);

        if (optLgnId.isEmpty()) {
          throw new JwtTokenException("Could not find lgnId in JWT token");
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(optLgnId.get());

        if (userDetails == null) {
          // 유효한 사용자가 아닌 경우 로그를 남기고 인증을 설정하지 않습니다.
          throw new UsernameNotFoundException("User not found for Login Id: " + optLgnId.get());
        }

        // TODO 어떤 역할을 하는지 확인해야함
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (JwtTokenException | UsernameNotFoundException ex) {
      log.error("JWT Authentication error", ex);
      SecurityContextHolder.clearContext();
      throw new JwtAuthenticationException(ex.getMessage());
    } catch (Exception ex) {
      log.error("Unexpected error during JWT authentication", ex);
      SecurityContextHolder.clearContext();
      throw new JwtAuthenticationException("Could not set user authentication in security context");
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
