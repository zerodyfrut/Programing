package com.chat.serverchat;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    
    @Autowired
    SimpMessagingTemplate messagingTemplate;
    @Autowired
    ChatMessageRepository chatMessageRepository;

    // @MessageMapping("/chat.send")
    // @SendTo("/topic/public")
    // public ChatMessage sendMessage(ChatMessage message){
    //     message.setTimestamp(LocalDateTime.now());
    //     return message;
    // }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/public")
    public ChatMessage newUser(ChatMessage message){
        message.setTimestamp(LocalDateTime.now());
        message.setType(ChatMessage.MessageType.JOIN);
        return message;
    }

    @MessageMapping("/chat.send")
    public void receiveMessage(ChatMessageDto messageDto){
        ChatMessage saved = chatMessageRepository.save(ChatMessage.builder()
        .sender(messageDto.getSender())
        .content(messageDto.getContent())
        .timestamp(LocalDateTime.now())
        .build()
        );
        messagingTemplate.convertAndSend("/topic/public",saved);
    }
}
