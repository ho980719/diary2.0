package com.ho.diary.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {
  "com.ho.diary.api",
  "com.ho.diary.core",
  "com.ho.diary.domain"
})
@EntityScan("com.ho.diary.domain")
public class DiaryApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(DiaryApiApplication.class, args);
    System.setProperty("spring.devtools.restart.enabled", "false");
  }

}
