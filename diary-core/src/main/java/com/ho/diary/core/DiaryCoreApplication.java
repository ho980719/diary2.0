package com.ho.diary.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DiaryCoreApplication {
  public static void main(String[] args) {
    SpringApplication.run(DiaryCoreApplication.class, args);
    System.setProperty("spring.config.name", "application,application-core");
  }
}
