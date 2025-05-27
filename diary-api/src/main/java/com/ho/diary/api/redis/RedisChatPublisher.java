package com.ho.diary.api.redis;

import com.ho.diary.api.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisChatPublisher {
  private final RedisTemplate<String, Object> redisTemplate;

  public void publish(String topic, ChatMessageDto message) {
    redisTemplate.convertAndSend(topic, message);
  }
}
