package com.ho.diary.core.exception;

import com.ho.diary.core.exception.enums.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BusinessExceptionTest {
  @Test
  void exception() {
    BusinessException ex = new BusinessException(ErrorCode.RESOURCE_NOT_FOUND);

    assertThat(ex.getErrorCode()).isEqualTo(ErrorCode.RESOURCE_NOT_FOUND);
    assertThat(ex.getMessage()).isEqualTo(ErrorCode.RESOURCE_NOT_FOUND.getMessage());
  }
}