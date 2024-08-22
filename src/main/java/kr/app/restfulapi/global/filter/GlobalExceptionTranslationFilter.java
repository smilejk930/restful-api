package kr.app.restfulapi.global.filter;

import java.io.IOException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.app.restfulapi.global.response.error.exception.SecurityExceptionHandler;
import lombok.RequiredArgsConstructor;


@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class GlobalExceptionTranslationFilter extends OncePerRequestFilter {

  private final SecurityExceptionHandler securityExceptionHandler;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (Exception ex) {
      securityExceptionHandler.handleException(request, response, ex);
    }
  }
}
