package com.ho.diary.core.exception.dto;

import com.ho.diary.core.exception.enums.ErrorCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
  private final String code;
  private final String message;
  private final int status;
  private final LocalDateTime timestamp;

  public ErrorResponse(ErrorCode errorCode) {
    this.code = errorCode.getCode();
    this.message = errorCode.getMessage();
    this.status = errorCode.getStatus().value();
    this.timestamp = LocalDateTime.now();
  }

}
