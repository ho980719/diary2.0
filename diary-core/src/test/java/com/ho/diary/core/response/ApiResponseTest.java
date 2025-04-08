package com.ho.diary.core.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ApiResponseTest {
  @Test
  void 성공응답_테스트() {
    String data = "hello";
    ApiResult<String> response = ApiResult.ok(data);

    assertThat(response.isSuccess()).isTrue();
    assertThat(response.getData()).isEqualTo("hello");
    assertThat(response.getMessage()).isNull();
  }

  @Test
  void 실패응답_테스트() {
    ApiResult<String> response = ApiResult.fail("에러 발생");

    assertThat(response.isSuccess()).isFalse();
    assertThat(response.getData()).isNull();
    assertThat(response.getMessage()).isEqualTo("에러 발생");
  }
}