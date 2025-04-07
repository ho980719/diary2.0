package com.ho.diary.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.ho.diary.*")
@EntityScan("com.ho.diary.domain")
public class DiaryApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(DiaryApiApplication.class, args);
  }

}
