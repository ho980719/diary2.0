package com.ho.diary.api.controller.auth.dto;

import com.ho.diary.core.encrypt.annotation.EncryptedField;

/*@Getter
@Setter
public class SignUpRequest {
  private String username;
  @EncryptedField
  private String password;
  private String email;
}*/
public record SignUpRequest(
  String username,
  @EncryptedField String password,
  String email
) {
}
