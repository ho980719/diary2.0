package com.ho.diary.core.config;

import com.ho.diary.core.encrypt.AESEncryptor;
import com.ho.diary.core.encrypt.EncryptedFieldAnnotationIntrospector;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer customizer(AESEncryptor encryptor) {
    return builder -> builder.annotationIntrospector(new EncryptedFieldAnnotationIntrospector(encryptor));
  }
}