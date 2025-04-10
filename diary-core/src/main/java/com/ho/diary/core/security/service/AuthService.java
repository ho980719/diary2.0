package com.ho.diary.core.security.service;

import com.ho.diary.core.security.dto.LoginRequest;
import com.ho.diary.core.security.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
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
    jwtTokenProvider.printSecret();

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
  }
}
