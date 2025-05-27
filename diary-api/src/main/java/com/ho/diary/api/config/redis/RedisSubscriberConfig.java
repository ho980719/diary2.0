package com.ho.diary.api.config.redis;

import com.ho.diary.api.redis.RedisChatSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class RedisSubscriberConfig {
  @Bean
  public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
    RedisChatSubscriber subscriber) {
    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.addMessageListener(subscriber, new PatternTopic("chatroom")); // 채널 이름

    return container;
  }
}
