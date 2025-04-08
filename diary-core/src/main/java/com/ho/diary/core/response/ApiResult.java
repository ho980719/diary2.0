package com.ho.diary.core.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResult<T> {
  private boolean success;
  private T data;
  private String message;

  public static <T> ApiResult<T> ok() {
    return new ApiResult<>(true, null, null);
  }

  public static <T> ApiResult<T> ok(T data) {
    return new ApiResult<>(true, data, null);
  }

  public static <T> ApiResult<T> fail(String message) {
    return new ApiResult<>(false, null, message);
  }
}