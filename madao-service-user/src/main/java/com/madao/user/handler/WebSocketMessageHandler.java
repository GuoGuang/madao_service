package com.madao.user.handler;

import com.madao.user.config.WsSessionManager;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Component
public class WebSocketMessageHandler extends TextWebSocketHandler {

    /**
     * socket 建立成功事件
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        Object token = session.getAttributes().get("token");
        String id = session.getUri().getQuery().substring(7);
        if (id != null) {
            // 用户连接成功，放入在线用户缓存
            WsSessionManager.add(id, session);
            Arrays.asList("1", "2", "3").forEach(item -> {
                try {
                    session.sendMessage(new TextMessage("server 发送给 " + id + " 消息 " + item + " " + LocalDateTime.now().toString()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } else {
            throw new RuntimeException("用户信息已经失效!");
        }
    }

    /**
     * 接收消息事件
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 获得客户端传来的消息
        String payload = message.getPayload();
        String userId = session.getUri().getQuery().substring(7);
        System.out.println("server 接收到 " + userId + " 发送的 " + payload);
        session.sendMessage(new TextMessage("server 发送给 " + userId + " 消息 " + payload + " " + LocalDateTime.now().toString()));
    }

    /**
     * socket 断开连接时
     *
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Object token = session.getAttributes().get("token");
        if (token != null) {
            // 用户退出，移除缓存
            WsSessionManager.remove(token.toString());
        }
    }


}
