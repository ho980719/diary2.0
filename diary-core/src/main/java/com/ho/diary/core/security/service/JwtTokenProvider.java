package com.ho.diary.core.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  @Value("${jwt.secret}")
  private String secret;

  private Key secretKey;
  private final long validityInMilliseconds = 3600000; // 1시간

  @PostConstruct
  protected void init() {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    this.secretKey = Keys.hmacShaKeyFor(keyBytes);
  }

  public String createToken(String username, List<String> roles) {
    Claims claims = Jwts.claims().setSubject(username);
    claims.put("roles", roles);

    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
      .setClaims(claims)
      .setIssuedAt(now)
      .setExpiration(validity)
      .signWith(secretKey, SignatureAlgorithm.HS256)
      .compact();
  }

  public Authentication getAuthentication(String token) {
    String username = getUsername(token);
    return new UsernamePasswordAuthenticationToken(username, "", getRoles(token));
  }

  public String getUsername(String token) {
    return Jwts.parserBuilder()
      .setSigningKey(secretKey)
      .build()
      .parseClaimsJws(token)
      .getBody()
      .getSubject();
  }

  public List<GrantedAuthority> getRoles(String token) {
    Claims claims = Jwts.parserBuilder()
      .setSigningKey(secretKey)
      .build()
      .parseClaimsJws(token)
      .getBody();

    List<String> roles = claims.get("roles", List.class);
    return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }
}