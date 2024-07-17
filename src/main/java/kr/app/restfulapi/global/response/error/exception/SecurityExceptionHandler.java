package kr.app.restfulapi.global.response.error.exception;

import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

  private final GlobalExceptionHandler globalExceptionHandler;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
    handleException(request, response, authException);
  }

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
      throws IOException, ServletException {
    handleException(request, response, accessDeniedException);
  }

  public void handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
    ServletWebRequest webRequest = new ServletWebRequest(request);
    ResponseEntity<Object> responseEntity;

    if (ex instanceof AuthenticationException exception) {
      responseEntity = globalExceptionHandler.handleAuthenticationException(exception, webRequest);
    } else if (ex instanceof AccessDeniedException exception) {
      responseEntity = globalExceptionHandler.handleAccessDeniedException(exception, webRequest);
    } else if (ex instanceof ResourceNotFoundException exception) {
      responseEntity = globalExceptionHandler.handleBusinessException(exception, webRequest);
    } else if (ex instanceof UnauthorizedException exception) {
      responseEntity = globalExceptionHandler.handleBusinessException(exception, webRequest);
    } else if (ex instanceof ForbiddenException exception) {
      responseEntity = globalExceptionHandler.handleBusinessException(exception, webRequest);
    } else if (ex instanceof JwtAuthenticationException exception) {
      responseEntity = globalExceptionHandler.handleBusinessException(exception, webRequest);
    } else {
      responseEntity = globalExceptionHandler.handleAllException(ex, webRequest);
    }

    writeResponse(response, responseEntity);
  }

  private void writeResponse(HttpServletResponse response, ResponseEntity<Object> responseEntity) throws IOException {
    response.setStatus(responseEntity.getStatusCode().value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(response.getOutputStream(), responseEntity.getBody());
  }
}
