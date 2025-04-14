package com.ho.diary.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class JpaConfig {
  @Bean
  @Primary
  public AuditorAware<Long> auditorProvider() {
    return new AuditorAwareImpl();
  }
}