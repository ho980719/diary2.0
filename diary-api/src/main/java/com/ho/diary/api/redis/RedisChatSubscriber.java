package com.ho.diary.api.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ho.diary.api.dto.ChatMessageDto;
import com.ho.diary.core.exception.BusinessException;
import com.ho.diary.core.exception.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisChatSubscriber implements MessageListener {
  private final ObjectMapper objectMapper;
  private final SimpMessagingTemplate messagingTemplate;

  @Override
  public void onMessage(Message message, byte[] pattern) {
    try {
      String msgBody = new String(message.getBody());
      ChatMessageDto chatMessage = objectMapper.readValue(msgBody, ChatMessageDto.class);

      // WebSocket 구독자에게 전송 (/topic/chatroom/{roomId})
      messagingTemplate.convertAndSend("/topic/chatroom/" + chatMessage.getRoomId(), chatMessage);

    } catch (Exception e) {
      throw new BusinessException(ErrorCode.MESSAGE_SEND_ERROR);
    }
  }
}
