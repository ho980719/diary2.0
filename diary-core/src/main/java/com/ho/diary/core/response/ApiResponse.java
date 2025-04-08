package com.ho.diary.core.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse<V> {
  public static <T> ResponseEntity<ApiResult<Void>> ok() {
    return ResponseEntity.ok(ApiResult.ok());
  }

  public static <T> ResponseEntity<ApiResult<T>> ok(T data) {
    return ResponseEntity.ok(ApiResult.ok(data));
  }

  public static <T> ResponseEntity<ApiResult<T>> fail(HttpStatus status, String message) {
    return ResponseEntity.status(status).body(ApiResult.fail(message));
  }
}
