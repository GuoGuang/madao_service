package com.madao.user.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.madao.model.dto.BarrageMessage;
import com.madao.user.config.WsSessionManager;
import com.madao.user.service.BarrageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class BarrageSocketEventHandler {

	final BarrageService barrageService;

	@OnConnect
	public void onConnect(SocketIOClient client) {
		log.info("客户端:" + client.getSessionId() + "已连接");
		//存储SocketIOClient，用于发送消息
		WsSessionManager.add(client.getSessionId().toString(), client);
		//回发消息
		client.sendEvent("message", "onConnect back");
		client.sendEvent("barrage-last-list", barrageService.findAll());
		sendBroadcast();
	}

	@OnDisconnect
	public void onDisconnect(SocketIOClient client) {
		log.info("客户端:" + client.getSessionId() + "断开连接");
		WsSessionManager.remove(client.getSessionId().toString());
	}

	@OnEvent(value = "barrage-send")
	public void onEvent(SocketIOClient client, AckRequest request, BarrageMessage data) {
		log.info("barrage-send事件：" + data);
		barrageService.insert(data);
	}

	public void sendBroadcast() {
		List<SocketIOClient> allSession = WsSessionManager.getAllSession();
		log.info("向{}广播消息", allSession.stream().map(SocketIOClient::getSessionId).toList());
		for (SocketIOClient client : allSession) {
			if (client.isChannelOpen()) {
				client.sendEvent("Broadcast", "当前时间" + System.currentTimeMillis());
			}
		}

	}
}