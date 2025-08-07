// package com.chat.serverchat.controller;

// import java.time.LocalDateTime;
// import java.time.LocalTime;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.messaging.handler.annotation.MessageMapping;
// import org.springframework.messaging.handler.annotation.SendTo;
// import org.springframework.stereotype.Controller;

// import com.chat.serverchat.entity.Content;
// import com.chat.serverchat.repository.ChatRepository;
// import com.chat.serverchat.repository.ContentRepository;

// @Controller
// public class MessageController {

//     @Autowired
//     ChatRepository chatRepository;

//     @Autowired
//     ContentRepository userRepository;

//     @MessageMapping("/chat.send")
//     @SendTo("/topic/public")
//     public Content sendMessage(Content message){
//         message.setTimestamp(LocalTime.now());
//         return message;
//     }

//     @MessageMapping("/chat.newUser")
//     @SendTo("/topic/public")
//     public Content newUser(Content message){
//         message.setTimestamp(LocalTime.now());
//         message.setTimestamp(LocalTime.now());
//         return message;
//     }
// }
