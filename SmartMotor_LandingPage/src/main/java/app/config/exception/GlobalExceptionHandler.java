package vn.viettel.app.config.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Optional.ofNullable;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ApiResponseData<String>> handleNotFoundException(HttpServletRequest request, NotFoundException ex) {
    log.error("NotFoundException {}\n", request.getRequestURI(), ex);

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ApiResponseData<>(HttpStatus.NOT_FOUND.name(), Collections.singletonList("Not found exception"), null));
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ApiResponseData<String>> handleValidationException(HttpServletRequest request, ValidationException ex) {
    log.error("ValidationException {}\n", request.getRequestURI(), ex);

    return ResponseEntity
        .badRequest()
        .body(new ApiResponseData<>(HttpStatus.BAD_REQUEST.name(), Collections.singletonList(ex.getMessage()), null));
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ApiResponseData<String>> handleMissingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException ex) {
    log.error("handleMissingServletRequestParameterException {}\n", request.getRequestURI(), ex);

    return ResponseEntity
        .badRequest()
        .body(new ApiResponseData<>(HttpStatus.BAD_REQUEST.name(), Collections.singletonList("Missing request parameter"), null));
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ApiResponseData<Map<String, String>>> handleMethodArgumentTypeMismatchException(HttpServletRequest request, MethodArgumentTypeMismatchException ex) {
    log.error("handleMethodArgumentTypeMismatchException {}\n", request.getRequestURI(), ex);

    Map<String, String> details = new HashMap<>();
    details.put("paramName", ex.getName());
    details.put("paramValue", ofNullable(ex.getValue()).map(Object::toString).orElse(""));
    details.put("errorMessage", ex.getMessage());

    return ResponseEntity
        .badRequest()
        .body(new ApiResponseData<>(HttpStatus.BAD_REQUEST.name(), Collections.singletonList("Method argument type mismatch"), details));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponseData<Map<String, String>>> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex) {
    log.error("handleMethodArgumentNotValidException {}\n", request.getRequestURI(), ex);

//    List<Map<String, String>> details = new ArrayList<>();
//    ex.getBindingResult()
//        .getFieldErrors()
//        .forEach(fieldError -> {
//          Map<String, String> detail = new HashMap<>();
//          detail.put("objectName", fieldError.getObjectName());
//          detail.put("field", fieldError.getField());
//          detail.put("rejectedValue", "" + fieldError.getRejectedValue());
//          detail.put("errorMessage", fieldError.getDefaultMessage());
//          details.add(detail);
//        });

    return ResponseEntity
        .badRequest()
        .body(new ApiResponseData<>(HttpStatus.BAD_REQUEST.name(), Collections.singletonList("Method argument validation failed"), null));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ApiResponseData<String>> handleAccessDeniedException(HttpServletRequest request, AccessDeniedException ex) {
    log.error("handleAccessDeniedException {}\n", request.getRequestURI(), ex);

    return ResponseEntity
        .status(HttpStatus.FORBIDDEN)
        .body(new ApiResponseData<>(HttpStatus.FORBIDDEN.name(), Collections.singletonList("Access denied"), null));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponseData<String>> handleInternalServerError(HttpServletRequest request, Exception ex) {
    log.error("handleInternalServerError {}\n", request.getRequestURI(), ex);

    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ApiResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR.name(), Collections.singletonList("Internal server error"), null));
  }

//  @ExceptionHandler(InternalAuthenticationServiceException.class)
//  public ResponseEntity<ApiResponseData<String>> handleBadCredentialsError(HttpServletRequest request, Exception ex) {
//    log.error("handleBadCredentialsError {}\n", request.getRequestURI(), ex);
//
//    return ResponseEntity
//        .status(HttpStatus.UNAUTHORIZED)
//        .body(new ApiResponseData<>(HttpStatus.UNAUTHORIZED.name(), Collections.singletonList(ex.getMessage()), null));
//  }

  @ExceptionHandler(ValidateException.class)
  public ResponseEntity<ApiResponseData<String>> handleValidateException(HttpServletRequest request, Exception ex) {
    log.error("handleValidateException {}\n", request.getRequestURI(), ex);

    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ApiResponseData<>(HttpStatus.BAD_REQUEST.name(), Collections.singletonList(ex.getMessage()), null));
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class ApiResponseData<T> {

    private String errorCode;
    private List<String> messages;
    private T data;

  }
}
