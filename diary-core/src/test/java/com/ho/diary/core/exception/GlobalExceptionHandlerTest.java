package com.ho.diary.core.exception;

import com.ho.diary.core.exception.dto.ErrorResponse;
import com.ho.diary.core.exception.enums.ErrorCode;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

  private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

  @Test
  void exceptionHandlerTest() {
    BusinessException ex = new BusinessException(ErrorCode.RESOURCE_NOT_FOUND);

    ResponseEntity<ErrorResponse> response = handler.handleBusinessException(ex);

    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getStatus()).isEqualTo(ErrorCode.RESOURCE_NOT_FOUND.getStatus());
    assertThat(response.getBody().getCode()).isEqualTo(ErrorCode.RESOURCE_NOT_FOUND.getCode());
    assertThat(response.getBody().getMessage()).isEqualTo(ErrorCode.RESOURCE_NOT_FOUND.getMessage());
  }
}