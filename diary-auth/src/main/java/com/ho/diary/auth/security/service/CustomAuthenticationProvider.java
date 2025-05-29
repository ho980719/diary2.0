package com.ho.diary.auth.security.service;

import com.ho.diary.core.encrypt.AESEncryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final UserDetailsService userDetailsService;
  private final AESEncryptor aesEncryptor;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String inputUsername = authentication.getName();
    String rawPassword = authentication.getCredentials().toString();

    UserDetails userDetails = userDetailsService.loadUserByUsername(inputUsername);

    if (!userDetails.getPassword().startsWith("{noop}")) {
      String decryptedPassword;
      try {
        decryptedPassword = aesEncryptor.decrypt(userDetails.getPassword());
      } catch (Exception e) {
        throw new BadCredentialsException("Decryption failed");
      }

      if (!rawPassword.equals(decryptedPassword)) {
        throw new BadCredentialsException("Invalid credentials");
      }
    }


    return new UsernamePasswordAuthenticationToken(
      userDetails,
      null,
      userDetails.getAuthorities()
    );
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
}