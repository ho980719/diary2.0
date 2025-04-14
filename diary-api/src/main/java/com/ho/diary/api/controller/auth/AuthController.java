package com.ho.diary.api.controller.auth;

import com.ho.diary.auth.security.dto.LoginRequest;
import com.ho.diary.auth.security.dto.TokenResponse;
import com.ho.diary.auth.security.service.AuthService;
import com.ho.diary.core.response.ApiResult;
import lombok.RequiredArgsConstructor;
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
  public ApiResult<TokenResponse> login(@RequestBody LoginRequest request) {
    return ApiResult.ok(authService.login(request));
  }
}
