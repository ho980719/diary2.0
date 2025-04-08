package com.ho.diary.core.aop;

import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import static org.assertj.core.api.Assertions.assertThat;

class LoggingAspectTest {

  @Test
  void loggingAspectTest() {
    DummyService target = new DummyService();
    AspectJProxyFactory factory = new AspectJProxyFactory(target);
    factory.addAspect(new LoggingAspect());

    DummyService proxy = factory.getProxy();
    String result = proxy.sayHello("Junho");

    assertThat(result).isEqualTo("Hello Junho");
    // 로그는 콘솔에 찍힘
  }

  static class DummyService {
    public String sayHello(String name) {
      return "Hello " + name;
    }
  }
}