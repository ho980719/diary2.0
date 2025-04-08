package com.ho.diary.core.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApiResultTest {

  @Test
  void success() {
    String data = "Hello World";
    ApiResult<String> response = ApiResult.ok(data);

    assertThat(response.isSuccess()).isTrue();
    assertThat(response.getData()).isEqualTo("Hello World");
    assertThat(response.getMessage()).isNull();
  }

  @Test
  void fail() {
    ApiResult<String> response = ApiResult.fail("Throws Exception");

    assertThat(response.isSuccess()).isFalse();
    assertThat(response.getData()).isNull();
    assertThat(response.getMessage()).isEqualTo("Throws Exception");
  }
}