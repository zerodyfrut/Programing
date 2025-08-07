package com.chat.serverchat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        config.enableSimpleBroker("/topic"); // 서버 -> 클라이언트에게 브로드캐스트
        config.setApplicationDestinationPrefixes("/app"); // 클라이언트 -> 서버 전송 prefix
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/ws-chat") 
        .setAllowedOriginPatterns("*") // 어떤 도메인이든 허용 
        .withSockJS(); // fallback 지원 //브라우저가 웹소켓을 지원하지 않을 경우 대체
    }
}
