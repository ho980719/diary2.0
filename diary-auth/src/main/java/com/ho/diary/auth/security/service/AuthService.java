package com.ho.diary.auth.security.service;

import com.ho.diary.auth.security.dto.LoginRequest;
import com.ho.diary.auth.security.dto.TokenResponse;
import com.ho.diary.auth.security.jwt.JwtTokenProvider;
import com.ho.diary.core.exception.BusinessException;
import com.ho.diary.core.exception.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;

  public TokenResponse login(LoginRequest request) {
    try {
      Authentication auth = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          request.getUsername(),
          request.getPassword()
        )
      );

      String token = jwtTokenProvider.createToken(
        auth.getName(),
        auth.getAuthorities().stream()
          .map(GrantedAuthority::getAuthority)
          .toList()
      );

      return new TokenResponse(token);

    } catch (BadCredentialsException e) {
      throw new BusinessException(ErrorCode.INVALID_USERNAME_PASSWORD);
    }
  }
}
