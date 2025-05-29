package com.ho.diary.auth.config;

import com.ho.diary.auth.security.exception.CustomAuthenticationEntryPoint;
import com.ho.diary.auth.security.filter.JwtAuthenticationFilter;
import com.ho.diary.auth.security.jwt.JwtTokenProvider;
import com.ho.diary.auth.security.service.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtTokenProvider jwtTokenProvider;
  private final CustomAuthenticationProvider customAuthenticationProvider;
  private final AuthenticationConfiguration authenticationConfiguration;
  private final AccessDeniedHandler customAccessDeniedHandler;
  private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
      .authenticationProvider(customAuthenticationProvider)
      .build();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
      .cors(Customizer.withDefaults())
      .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(
        auth -> auth.requestMatchers(
          "/api/v1/auth/**",
          "/test/**",
          "/ws-chat/**")
          .permitAll()
          .anyRequest().authenticated()
      )
      .exceptionHandling(
        ex -> ex.accessDeniedHandler(customAccessDeniedHandler)
          .authenticationEntryPoint(customAuthenticationEntryPoint)
      )
      .addFilterAfter(new JwtAuthenticationFilter(jwtTokenProvider, customAuthenticationEntryPoint),
        UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}

