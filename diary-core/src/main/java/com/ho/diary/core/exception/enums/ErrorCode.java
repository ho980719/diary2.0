package com.ho.diary.core.exception.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  INVALID_INPUT_VALUE("INVALID_INPUT_VALUE", "잘못된 입력입니다.", HttpStatus.BAD_REQUEST),
  RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "요청하신 데이터를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "서버 에러가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
  INVALID_JSON("INVALID_JSON", "올바르지 않은 JSON 형식입니다.", HttpStatus.BAD_REQUEST),
  MISSING_REQUEST_PARAMETER("MISSING_REQUEST_PARAMETER", "필수 요청 파라미터가 누락되었습니다.", HttpStatus.BAD_REQUEST),
  METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "지원하지 않는 HTTP 메서드입니다.", HttpStatus.METHOD_NOT_ALLOWED),
  ACCESS_DENIED("ACCESS_DENIED", "접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
  UNAUTHORIZED("UNAUTHORIZED", "인증이 필요합니다.", HttpStatus.UNAUTHORIZED),
  ;

  private final String code;
  private final String message;
  private final HttpStatus status;
}