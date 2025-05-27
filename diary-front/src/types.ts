// src/types.ts
export type MessageType = "TALK" | "ENTER" | "LEAVE";

export interface ChatMessage {
  type: MessageType;
  roomId: string;
  from: string;
  message: string;
  timestamp?: number;
}
