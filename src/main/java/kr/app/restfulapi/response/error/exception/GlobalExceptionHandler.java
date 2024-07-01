package kr.app.restfulapi.response.error.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import kr.app.restfulapi.response.error.ErrorResponse;
import kr.app.restfulapi.response.error.ErrorStatus;
import kr.app.restfulapi.response.error.FieldError;
import kr.app.restfulapi.sample.user.UserNotFoundException;

/**
 * 예외처리를 위한 핸들러 클래스
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
    ErrorResponse errorResponse =
        ErrorResponse.errorStatus().status(ErrorStatus.INTERNAL_SERVER_ERROR).errors(FieldError.of(null, request.getDescription(false), ex.getMessage())).build();
    return new ResponseEntity<>(errorResponse, ErrorStatus.INTERNAL_SERVER_ERROR.getStatus());
  }

  @ExceptionHandler(BusinessException.class)
  protected final ResponseEntity<Object> handleBusinessException(BusinessException ex) {
    ErrorResponse errorResponse = ErrorResponse.errorStatus().status(ex.getStatus()).errors(ex.getErrors()).build();
    return new ResponseEntity<>(errorResponse, ex.getStatus().getStatus());
  }

  /* User가 존재하지 않을 때 */
  @ExceptionHandler(UserNotFoundException.class)
  protected final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.errorStatus().status(ErrorStatus.NOT_FOUND).errors(FieldError.of(null, ex.getMessage(), request.getDescription(false))).build();
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  /* DTO validation 오류 발생 시 */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.httpStatusCode().status(status).errors(FieldError.of(ex.getBindingResult())).build();
    return new ResponseEntity<>(errorResponse, ErrorStatus.find(status).getStatus());
  }

  @Override
  protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.httpStatusCode().status(status).errors(FieldError.of(null, request.getDescription(false), ex.getMessage())).build();
    return new ResponseEntity<>(errorResponse, ErrorStatus.find(status).getStatus());
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.httpStatusCode().status(status).errors(FieldError.of("HTTP METHOD", ex.getMethod(), ex.getMessage())).build();
    return new ResponseEntity<>(errorResponse, ErrorStatus.find(status).getStatus());
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    ErrorResponse errorResponse =
        ErrorResponse.errorStatus().status(ErrorStatus.HTTP_MESSAGE_NOT_READABLE).errors(FieldError.of("HTTP BODY", request.getDescription(false), ex.getMessage())).build();
    return new ResponseEntity<>(errorResponse, ErrorStatus.HTTP_MESSAGE_NOT_READABLE.getStatus());
  }


}
