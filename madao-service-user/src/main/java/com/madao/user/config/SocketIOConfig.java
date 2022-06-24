package com.madao.user.config;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.corundumstudio.socketio.listener.ExceptionListenerAdapter;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
@org.springframework.context.annotation.Configuration
@ConfigurationProperties(prefix = "socketio")
public class SocketIOConfig {

	private String host;
	private String path;
	private Integer port;
	private int bossCount;
	private int workCount;
	private boolean allowCustomRequests;
	private int upgradeTimeout;
	private int pingTimeout;
	private int pingInterval;

	@Bean
	public SocketIOServer socketIOServer() {
		SocketConfig socketConfig = new SocketConfig();
		socketConfig.setTcpNoDelay(true);
		socketConfig.setSoLinger(0);
		socketConfig.setAcceptBackLog(4000);
		socketConfig.setReuseAddress(true);
		Configuration config = new Configuration();
		config.setSocketConfig(socketConfig);
		config.setContext(path);
		config.setHostname(host);
		config.setPort(port);
		config.setBossThreads(bossCount);
		config.setWorkerThreads(workCount);
		config.setAllowCustomRequests(allowCustomRequests);
		config.setUpgradeTimeout(upgradeTimeout);
		config.setPingTimeout(pingTimeout);
		config.setPingInterval(pingInterval);
		config.setExceptionListener(new SocketExceptionListener());
		return new SocketIOServer(config);
	}

	/**
	 * 用于扫描netty-socketio的注解，比如 @OnConnect、@OnEvent
	 */
	@Bean
	public SpringAnnotationScanner springAnnotationScanner() {
		return new SpringAnnotationScanner(socketIOServer());
	}

	@Slf4j
	static class SocketExceptionListener extends ExceptionListenerAdapter {
		@Override
		public boolean exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
			log.error("socket异常：{}", e.getMessage(), e);
			ctx.close();
			return true;
		}
	}

}