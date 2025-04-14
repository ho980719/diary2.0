package com.ho.diary.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {
  "com.ho.diary.api",
  "com.ho.diary.auth",
  "com.ho.diary.core",
  "com.ho.diary.domain"
})
@EnableJpaAuditing
@EntityScan("com.ho.diary.domain")
public class DiaryApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(DiaryApiApplication.class, args);
    System.setProperty("spring.devtools.restart.enabled", "false");
  }

}


