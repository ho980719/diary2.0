package com.ho.diary.api.controller;

import com.ho.diary.core.response.ApiResponse;
import com.ho.diary.core.response.ApiResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping
  public ResponseEntity<ApiResult<Void>> test() {
    return ApiResponse.ok();
  }
}
