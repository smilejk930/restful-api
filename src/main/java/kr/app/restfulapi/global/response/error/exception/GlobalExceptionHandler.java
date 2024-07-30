package kr.app.restfulapi.global.response.error.exception;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import kr.app.restfulapi.global.response.error.ErrorResponse;
import kr.app.restfulapi.global.response.error.ErrorStatus;
import kr.app.restfulapi.global.response.error.FieldError;
import lombok.extern.slf4j.Slf4j;

/**
 * 예외처리를 위한 핸들러 클래스
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.errorStatus()
        .status(ErrorStatus.INTERNAL_SERVER_ERROR)
        .errors(FieldError.of(null, request.getDescription(false), "N/A"))
        .build();
    return new ResponseEntity<>(errorResponse, ErrorStatus.INTERNAL_SERVER_ERROR.getStatus());
  }

  @ExceptionHandler(BusinessException.class)
  protected final ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
    ErrorResponse errorResponse;
    List<FieldError> errors = ex.getErrors();
    if (errors != null && errors.size() == 1) {
      errors = errors.stream()
          .filter(error -> "N/A".equals(error.getField()) && "N/A".equals(error.getValue()))
          .flatMap(error -> FieldError.of(null, request.getDescription(false), error.getReason()).stream())// flatMap()을 사용하여 중첩된 리스트를 평탄화합니다.
                                                                                                           // FieldError.of()가 List<FieldError>를
                                                                                                           // 반환하므로, flatMap()을 통해 각 리스트의 요소를 스트림으로
                                                                                                           // 변환하고 이를 평탄화
          .toList(); // Stream을 List로 수집

      // 만약 필터링 후 리스트가 비어있다면 원래의 에러 리스트를 유지
      if (errors.isEmpty()) {
        errors = ex.getErrors();
      }
    }
    errorResponse = ErrorResponse.errorStatus().status(ex.getStatus()).errors(errors).build();
    return new ResponseEntity<>(errorResponse, ex.getStatus().getStatus());
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.errorStatus()
        .status(ErrorStatus.UNAUTHORIZED)
        .errors(FieldError.of(null, ex.getMessage(), request.getDescription(false)))
        .build();
    return new ResponseEntity<>(errorResponse, ErrorStatus.UNAUTHORIZED.getStatus());
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
    ErrorResponse errorResponse =
        ErrorResponse.errorStatus().status(ErrorStatus.FORBIDDEN).errors(FieldError.of(null, ex.getMessage(), request.getDescription(false))).build();
    return new ResponseEntity<>(errorResponse, ErrorStatus.FORBIDDEN.getStatus());
  }

  @ExceptionHandler(JwtAuthenticationException.class)
  public ResponseEntity<Object> handleJwtAuthenticationException(JwtAuthenticationException ex, WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.errorStatus()
        .status(ErrorStatus.UNAUTHORIZED)
        .errors(FieldError.of(null, ex.getMessage(), request.getDescription(false)))
        .build();
    return new ResponseEntity<>(errorResponse, ErrorStatus.UNAUTHORIZED.getStatus());
  }

  @ExceptionHandler(BadCredentialsException.class)
  protected final ResponseEntity<Object> handleBadCredentialsException(Exception ex, WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.errorStatus()
        .status(ErrorStatus.UNAUTHORIZED)
        .errors(FieldError.of(null, ex.getMessage(), request.getDescription(false)))
        .build();
    return new ResponseEntity<>(errorResponse, ErrorStatus.UNAUTHORIZED.getStatus());
  }

  @ExceptionHandler(LockedException.class)
  protected final ResponseEntity<Object> handleLockedException(Exception ex, WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.errorStatus()
        .status(ErrorStatus.UNAUTHORIZED)
        .errors(FieldError.of(null, ex.getMessage(), request.getDescription(false)))
        .build();
    return new ResponseEntity<>(errorResponse, ErrorStatus.UNAUTHORIZED.getStatus());
  }

  /*
  @ExceptionHandler(value = {BadCredentialsException.class, LockedException.class})
  protected final ResponseEntity<Object> handleBadCredentialsOrLockedException(Exception ex, WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.errorStatus()
        .status(ErrorStatus.UNAUTHORIZED)
        .errors(FieldError.of(null, ex.getMessage(), request.getDescription(false)))
        .build();
    return new ResponseEntity<>(errorResponse, ErrorStatus.UNAUTHORIZED.getStatus());
  }
  */

  /* @Validated 또는 @Valid 사용하여 오류 발생 시 */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.errorStatus().status(ErrorStatus.BAD_REQUEST).errors(FieldError.of(ex.getBindingResult())).build();
    return new ResponseEntity<>(errorResponse, ErrorStatus.BAD_REQUEST.getStatus());
  }

  @Override
  protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    ErrorResponse errorResponse =
        ErrorResponse.httpStatusCode().status(status).errors(FieldError.of(null, request.getDescription(false), ex.getMessage())).build();
    return new ResponseEntity<>(errorResponse, ErrorStatus.find(status).getStatus());
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers,
      HttpStatusCode status, WebRequest request) {
    ErrorResponse errorResponse =
        ErrorResponse.httpStatusCode().status(status).errors(FieldError.of("HTTP METHOD", ex.getMethod(), ex.getMessage())).build();
    return new ResponseEntity<>(errorResponse, ErrorStatus.find(status).getStatus());
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.errorStatus()
        .status(ErrorStatus.HTTP_MESSAGE_NOT_READABLE)
        .errors(FieldError.of("HTTP BODY", request.getDescription(false), ex.getMessage()))
        .build();
    return new ResponseEntity<>(errorResponse, ErrorStatus.HTTP_MESSAGE_NOT_READABLE.getStatus());
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.errorStatus()
        .status(ErrorStatus.BAD_REQUEST)
        .errors(FieldError.of(null, request.getDescription(false), ex.getMessage()))
        .build();
    return new ResponseEntity<>(errorResponse, ErrorStatus.find(status).getStatus());
  }

}
