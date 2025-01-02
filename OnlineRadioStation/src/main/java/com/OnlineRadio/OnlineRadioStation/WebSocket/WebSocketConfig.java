package com.OnlineRadio.OnlineRadioStation.WebSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final RadioWebSocketHandler radioWebSocketHandler;

    public WebSocketConfig(RadioWebSocketHandler radioWebSocketHandler) {
        this.radioWebSocketHandler = radioWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(radioWebSocketHandler, "/ws/radio").setAllowedOrigins("*");
    }
}

