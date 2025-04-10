package com.ho.diary.core.exception;

import com.ho.diary.core.exception.dto.ErrorResponse;
import com.ho.diary.core.exception.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
    ErrorCode errorCode = ex.getErrorCode();
    return ResponseEntity
      .status(errorCode.getStatus())
      .body(new ErrorResponse(errorCode));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
    return ResponseEntity
      .badRequest()
      .body(new ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, ex.getBindingResult()));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
    return ResponseEntity
      .badRequest()
      .body(new ErrorResponse(ErrorCode.INVALID_JSON));
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ErrorResponse> handleMissingParams(MissingServletRequestParameterException ex) {
    return ResponseEntity
      .badRequest()
      .body(new ErrorResponse(ErrorCode.MISSING_REQUEST_PARAMETER));
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
    return ResponseEntity
      .status(ErrorCode.METHOD_NOT_ALLOWED.getStatus())
      .body(new ErrorResponse(ErrorCode.METHOD_NOT_ALLOWED));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex) {
    return ResponseEntity
      .status(ErrorCode.ACCESS_DENIED.getStatus())
      .body(new ErrorResponse(ErrorCode.ACCESS_DENIED));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception ex) {
    ErrorCode fallback = ErrorCode.INTERNAL_SERVER_ERROR;
    return ResponseEntity
      .status(fallback.getStatus())
      .body(new ErrorResponse(fallback));
  }
}
