package com.ho.diary.core.encrypt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

@Component
public class AESEncryptor {

  private final SecretKeySpec secretKeySpec;
  private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
  private static final String KEY_ALGORITHM = "AES";

  public AESEncryptor(@Value("${aes.secret-key}") String secretKey) {
    byte[] key = Arrays.copyOf(secretKey.getBytes(StandardCharsets.UTF_8), 16); // 128bit
    this.secretKeySpec = new SecretKeySpec(key, KEY_ALGORITHM);
  }

  public String encrypt(String plainText) {
    try {
      Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
      byte[] iv = new byte[16];
      new SecureRandom().nextBytes(iv);
      IvParameterSpec ivSpec = new IvParameterSpec(iv);
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
      byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

      ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + encrypted.length);
      byteBuffer.put(iv);
      byteBuffer.put(encrypted);
      return Base64.getEncoder().encodeToString(byteBuffer.array());
    } catch (Exception e) {
      throw new RuntimeException("Encryption failed", e);
    }
  }

  public String decrypt(String cipherText) {
    try {
      ByteBuffer byteBuffer = ByteBuffer.wrap(Base64.getDecoder().decode(cipherText));
      byte[] iv = new byte[16];
      byteBuffer.get(iv);
      byte[] encrypted = new byte[byteBuffer.remaining()];
      byteBuffer.get(encrypted);

      Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
      IvParameterSpec ivSpec = new IvParameterSpec(iv);
      cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
      byte[] decrypted = cipher.doFinal(encrypted);
      return new String(decrypted, StandardCharsets.UTF_8);
    } catch (Exception e) {
      throw new RuntimeException("Decryption failed", e);
    }
  }
}