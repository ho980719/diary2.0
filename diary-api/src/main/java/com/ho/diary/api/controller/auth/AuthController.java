package com.ho.diary.api.controller.auth;

import com.ho.diary.core.response.ApiResponse;
import com.ho.diary.core.response.ApiResult;
import com.ho.diary.core.security.dto.LoginRequest;
import com.ho.diary.core.security.dto.TokenResponse;
import com.ho.diary.core.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<ApiResult<TokenResponse>> login(@RequestBody LoginRequest request) {
    return ApiResponse.ok(authService.login(request));
  }
}
