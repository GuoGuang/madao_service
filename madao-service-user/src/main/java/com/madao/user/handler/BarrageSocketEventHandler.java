package com.madao.user.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.madao.model.dto.BarrageMessage;
import com.madao.user.config.WsSessionManager;
import com.madao.user.service.BarrageService;
import com.madao.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
		List<Map<String, Object>> maps = JsonUtil.jsonToListMap("[{\"text\":\"飞机飞过天空，天空之城~\",\"style\":{\"size\":2,\"color\":7}},{\"text\":\"闻一闻亲手浇的花儿陪着小狗儿在晚霞里遛遛弯儿一会儿 舒服一会儿是一会儿\",\"style\":{\"size\":1,\"color\":3}},{\"text\":\"我是森林中的布谷鸟 家住在美丽的半山腰看太阳落下去又回来世界太多美妙\",\"style\":{\"size\":2,\"color\":2}},{\"text\":\"喝得越多 嗓门越高 身份的象征就是手里的包\",\"style\":{\"size\":0,\"color\":5}},{\"text\":\"飞蝴蝶跳着动人的舞蹈她的秘密没有人知道 美丽的白云悄悄哭泣 那是雨水的味道\",\"style\":{\"size\":2,\"color\":4}},{\"text\":\"Knock-knock-knocking on heaven's door ~\",\"style\":{\"size\":1,\"color\":6}},{\"text\":\"其实界面我觉得可以再优化下。\",\"style\":{\"size\":2,\"color\":7},\"date\":1575890560063},{\"text\":\"Wt???\",\"style\":{\"size\":2,\"color\":7},\"date\":1575890560063}]\n");
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
		log.info("barrage-create事件：" + data);
		barrageService.insert(data);
	}

	public void sendBroadcast() {
		List<SocketIOClient> allSession = WsSessionManager.getAllSession();
		log.info("向{}广播消息", allSession.stream().map(SocketIOClient::getSessionId));
		for (SocketIOClient client : allSession) {
			if (client.isChannelOpen()) {
				client.sendEvent("Broadcast", "当前时间" + System.currentTimeMillis());
			}
		}

	}
}