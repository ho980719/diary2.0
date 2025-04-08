package com.ho.diary.api.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnableAspectJAutoProxy
@Import(LoggingAspectIntegrationTest.DummyService.class)
class LoggingAspectIntegrationTest {

  @Autowired
  DummyService dummyService;

  @Test
  void loggingAspectIntegrationTest() {
    String result = dummyService.greet("Junho");

    assertThat(result).isEqualTo("Hello Junho");
    // 콘솔에 로그가 찍히는지 확인!
  }

  @Service
  public static class DummyService {
    public String greet(String name) {
      return "Hello " + name;
    }
  }
}