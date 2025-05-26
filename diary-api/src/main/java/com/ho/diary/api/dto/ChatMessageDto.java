package com.ho.diary.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {
  private Long sendUserId;      // 메시지 보낸 사람
  private Long roomId;    // 채팅방 아이디
  private String message;   // 실제 메시지 내용
  private LocalDateTime sendDateTime;
}
