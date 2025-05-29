package com.ho.diary.core.encrypt;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class EncryptedFieldSerializer extends JsonSerializer<String> {

  private final AESEncryptor encryptor;

  public EncryptedFieldSerializer(AESEncryptor encryptor) {
    this.encryptor = encryptor;
  }

  @Override
  public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    if (value != null) {
      gen.writeString(encryptor.decrypt(value));
    } else {
      gen.writeNull();
    }
  }
}
