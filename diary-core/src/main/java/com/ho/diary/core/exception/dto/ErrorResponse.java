package com.ho.diary.core.exception.dto;

import com.ho.diary.core.exception.enums.ErrorCode;
import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {
  private final String code;
  private final String message;
  private final int status;
  private final LocalDateTime timestamp;
  private final List<FieldValidationError> errors;

  public ErrorResponse(ErrorCode errorCode) {
    this.code = errorCode.getCode();
    this.message = errorCode.getMessage();
    this.status = errorCode.getStatus().value();
    this.timestamp = LocalDateTime.now();
    this.errors = null;
  }

  // Validation 예외용 생성자
  public ErrorResponse(ErrorCode errorCode, BindingResult bindingResult) {
    this.code = errorCode.getCode();
    this.message = errorCode.getMessage();
    this.status = errorCode.getStatus().value();
    this.timestamp = LocalDateTime.now();
    this.errors = bindingResult.getFieldErrors().stream()
      .map(error -> new FieldValidationError(error.getField(), error.getDefaultMessage()))
      .collect(Collectors.toList());
  }

  @Getter
  public static class FieldValidationError {
    private final String field;
    private final String message;

    public FieldValidationError(String field, String message) {
      this.field = field;
      this.message = message;
    }
  }
}
