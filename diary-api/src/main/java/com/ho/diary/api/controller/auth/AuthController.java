package com.ho.diary.api.controller.auth;

import com.ho.diary.core.response.ApiResponse;
import com.ho.diary.core.response.ApiResult;
import com.ho.diary.core.security.dto.LoginRequest;
import com.ho.diary.core.security.dto.TokenResponse;
import com.ho.diary.core.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @GetMapping("/test")
  public Map<String, LocalDateTime> getNow() {
    return Map.of("now", LocalDateTime.now());
  }

  @PostMapping("/login")
  public ResponseEntity<ApiResult<TokenResponse>> login(@RequestBody LoginRequest request) {
    return ApiResponse.ok(authService.login(request));
  }
}
