package com.ho.diary.core.exception;

import com.ho.diary.core.exception.dto.ErrorResponse;
import com.ho.diary.core.exception.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
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

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception ex) {
    ErrorCode fallback = ErrorCode.INTERNAL_SERVER_ERROR;
    return ResponseEntity
      .status(fallback.getStatus())
      .body(new ErrorResponse(fallback));
  }
}
