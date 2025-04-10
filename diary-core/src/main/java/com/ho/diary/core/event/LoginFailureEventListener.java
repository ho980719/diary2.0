package com.ho.diary.core.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoginFailureEventListener {

  @EventListener
  public void onFailure(AuthenticationFailureBadCredentialsEvent event) {
    String username = (String) event.getAuthentication().getPrincipal();

    // @todo 실패 횟수 DB 저장, Redis 증가 등 가능
    log.info("{} : login fail!!", username);
  }
}
