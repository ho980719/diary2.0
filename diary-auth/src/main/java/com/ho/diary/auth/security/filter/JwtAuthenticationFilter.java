package com.ho.diary.auth.security.filter;

import com.ho.diary.auth.security.exception.CustomAuthenticationEntryPoint;
import com.ho.diary.auth.security.jwt.JwtTokenProvider;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;
  private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

  public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider,
    CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
    this.jwtTokenProvider = jwtTokenProvider;
    this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
    throws ServletException, IOException {
    if (request.getRequestURI().startsWith("/api/v1/auth") || request.getRequestURI().startsWith("/ws-chat")) {
      chain.doFilter(request, response);
      return;
    }

    try {

      String token = resolveToken(request);

      if (token != null && jwtTokenProvider.validateToken(token)) {
        Authentication auth = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
      } else {
        throw new JwtException("Invalid token");
      }

      chain.doFilter(request, response);
    } catch (JwtException | IllegalArgumentException e) {
      // 인증 실패 시 직접 AuthenticationEntryPoint에게 위임
      SecurityContextHolder.clearContext();
      customAuthenticationEntryPoint.commence(request, response, new InsufficientAuthenticationException(e.getMessage()));
    }
  }

  private String resolveToken(HttpServletRequest request) {
    String bearer = request.getHeader("Authorization");
    if (bearer != null && bearer.startsWith("Bearer ")) {
      return bearer.substring(7);
    }
    return null;
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    String path = request.getRequestURI();
    return path.equals("/api/v1/auth/login"); // login 요청은 JWT 필터 생략
  }
}
