package com.ho.diary.api.controller.chat;


import com.ho.diary.api.dto.ChatMessageDto;
import com.ho.diary.api.redis.RedisChatPublisher;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

  private final RedisChatPublisher publisher;

  public ChatController(RedisChatPublisher publisher) {
    this.publisher = publisher;
  }

  @MessageMapping("/chat.send")
  public void sendMessage(ChatMessageDto message) {
    publisher.publish("chatroom", message); // Redis로 전송
  }
}
