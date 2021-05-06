package com.madao.user.config;

import com.madao.user.handler.WebSocketMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@EnableWebSocket
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketMessageHandler webSocketMessageHandler;
    private final WebSocketInterceptor webSocketInterceptor;

    @Autowired
    public WebSocketConfig(WebSocketMessageHandler webSocketMessageHandler, WebSocketInterceptor webSocketInterceptor) {
        this.webSocketMessageHandler = webSocketMessageHandler;
        this.webSocketInterceptor = webSocketInterceptor;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(webSocketMessageHandler, "message")
                .addInterceptors(webSocketInterceptor)
                .setAllowedOrigins("*");
    }

}
