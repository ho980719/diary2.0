// src/components/ChatRoom.tsx
import React, { useEffect, useRef, useState } from "react";
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";
import { ChatMessage } from "../../types";

interface ChatRoomProps {
  username: string;
  roomId: string;
}

const ChatRoom: React.FC<ChatRoomProps> = ({ username, roomId }) => {
  const [messages, setMessages] = useState<ChatMessage[]>([]);
  const [input, setInput] = useState("");
  const stompClientRef = useRef<Client | null>(null);

  useEffect(() => {
    const socket = new SockJS("http://localhost:8080/ws-chat"); // WebSocket endpoint
    const client = new Client({
      webSocketFactory: () => socket,
      debug: (str) => console.log(str),
      reconnectDelay: 5000,
      onConnect: () => {
        client.subscribe(`/topic/chatroom/${roomId}`, (message) => {
          const received: ChatMessage = JSON.parse(message.body);
          setMessages((prev) => [...prev, received]);
        });

        client.publish({
          destination: "/app/chat.send",
          body: JSON.stringify({
            type: "ENTER",
            roomId,
            from: username,
            message: `${username}님이 입장했습니다.`,
          }),
        });
      },
    });

    client.activate();
    stompClientRef.current = client;

    return () => {
      client.deactivate();
    };
  }, [roomId, username]);

  const sendMessage = () => {
    if (input.trim() && stompClientRef.current?.connected) {
      const message: ChatMessage = {
        type: "TALK",
        roomId,
        from: username,
        message: input,
        timestamp: Date.now(),
      };

      stompClientRef.current.publish({
        destination: "/app/chat.send",
        body: JSON.stringify(message),
      });

      setInput("");
    }
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>채팅방: {roomId}</h2>
      <div
        style={{
          border: "1px solid gray",
          height: 300,
          overflowY: "scroll",
          marginBottom: 10,
          padding: 10,
        }}
      >
        {messages.map((msg, idx) => (
          <div key={idx}>
            <strong>{msg.from}:</strong> {msg.message}
          </div>
        ))}
      </div>

      <input
        type="text"
        value={input}
        onChange={(e) => setInput(e.target.value)}
        onKeyDown={(e) => e.key === "Enter" && sendMessage()}
        placeholder="메시지를 입력하세요"
        style={{ width: "70%", marginRight: 10 }}
      />
      <button onClick={sendMessage}>보내기</button>
    </div>
  );
};

export default ChatRoom;
