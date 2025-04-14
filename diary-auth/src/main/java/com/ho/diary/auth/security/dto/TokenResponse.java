package com.ho.diary.auth.security.dto;

import lombok.Getter;

@Getter
public class TokenResponse {
  private String token;
  private String tokenType = "Bearer";

  public TokenResponse(String token) {
    this.token = token;
  }
}
