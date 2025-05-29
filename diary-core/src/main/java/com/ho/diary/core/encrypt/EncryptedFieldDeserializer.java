package com.ho.diary.core.encrypt;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class EncryptedFieldDeserializer extends JsonDeserializer<String> {

  private final AESEncryptor encryptor;

  public EncryptedFieldDeserializer(AESEncryptor encryptor) {
    this.encryptor = encryptor;
  }

  @Override
  public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    String encryptedValue = p.getValueAsString();
    return encryptedValue != null ? encryptor.encrypt(encryptedValue) : null;
  }
}
