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
  INVALID_JWT("UNAUTHORIZED", "토큰 인증이 필요합니다.", HttpStatus.UNAUTHORIZED),
  INVALID_USERNAME_PASSWORD("INVALID_USERNAME_PASSWORD", "아이디/비밀번호를 확인해주세요.", HttpStatus.UNAUTHORIZED),
  FILE_UPLOAD_ERROR("FILE_UPLOAD_ERROR", "파일 업로드시 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
  MESSAGE_SEND_ERROR("MESSAGE_SEND_ERROR", "메세지 전송중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
  DUPLICATE_USERNAME("DUPLICATE_USERNAME", "이미 존재하는 사용자명입니다.", HttpStatus.CONFLICT),
  DUPLICATE_EMAIL("DUPLICATE_EMAIL", "이미 존재하는 이메일입니다.", HttpStatus.CONFLICT),
  DISABLED_USER("DISABLED_USER", "비활성화된 회원입니다.", HttpStatus.FORBIDDEN),
  ;

  private final String code;
  private final String message;
  private final HttpStatus status;
}