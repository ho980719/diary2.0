package com.ho.diary.core.exception.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  INVALID_INPUT_VALUE("INVALID_INPUT_VALUE", "잘못된 입력입니다.", HttpStatus.BAD_REQUEST),
  RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "요청하신 데이터를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "서버 에러가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),;

  private final String code;
  private final String message;
  private final HttpStatus status;
}