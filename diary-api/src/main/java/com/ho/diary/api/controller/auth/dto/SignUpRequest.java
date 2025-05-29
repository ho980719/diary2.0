package com.ho.diary.api.controller.auth.dto;

public record SignUpRequest(
  String username,
  String password,
  String email
) {

}
