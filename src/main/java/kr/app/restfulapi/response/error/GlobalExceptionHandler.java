package kr.app.restfulapi.response.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import kr.app.restfulapi.sample.user.UserNotFoundException;

/**
 * 예외처리를 위한 핸들러 클래스
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ErrorResponse> handleAllException(Exception ex, WebRequest request) throws Exception {
    ErrorResponse errorResponse =
        ErrorResponse.builder().status(ErrorStatus.INTERNAL_SERVER_ERROR.getStatus()).errors(FieldError.of(null, request.getDescription(false), ex.getMessage())).build();
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /* User가 존재하지 않을 때 */
  @ExceptionHandler(UserNotFoundException.class)
  public final ResponseEntity<ErrorResponse> handleUserNotFoundException(Exception ex, WebRequest request) {
    ErrorResponse errorResponse =
        ErrorResponse.builder().status(ErrorStatus.NOT_FOUND.getStatus()).errors(FieldError.of(null, ex.getMessage(), request.getDescription(false))).build();
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  /* DTO validation 오류 발생 시 */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.builder().status(status).errors(FieldError.of(ex.getBindingResult())).build();
    return new ResponseEntity<Object>(errorResponse, ErrorStatus.find(status).getStatus());
  }

  @Override
  protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.builder().status(status).errors(FieldError.of(null, request.getDescription(false), ex.getMessage())).build();
    return new ResponseEntity<Object>(errorResponse, ErrorStatus.find(status).getStatus());
  }

}
