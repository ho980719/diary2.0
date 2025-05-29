package com.ho.diary.core.encrypt;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.ho.diary.core.encrypt.annotation.EncryptedField;

public class EncryptedFieldAnnotationIntrospector extends JacksonAnnotationIntrospector {

  private final AESEncryptor encryptor;

  public EncryptedFieldAnnotationIntrospector(AESEncryptor encryptor) {
    this.encryptor = encryptor;
  }

  @Override
  public Object findSerializer(Annotated a) {
    if (a.hasAnnotation(EncryptedField.class)) {
      return new EncryptedFieldSerializer(encryptor);
    }
    return null;
  }

  @Override
  public Object findDeserializer(Annotated a) {
    if (a.hasAnnotation(EncryptedField.class)) {
      return new EncryptedFieldDeserializer(encryptor);
    }
    return null;
  }
}
