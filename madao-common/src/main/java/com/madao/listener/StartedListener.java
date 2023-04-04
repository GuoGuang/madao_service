package com.madao.listener;

import com.madao.constant.FeignConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

/**
 * The method executed after the application is started.
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StartedListener implements ApplicationListener<ApplicationStartedEvent> {

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		try {
			this.printStartInfo();
		} catch (UnknownHostException e) {
			log.error(e.getMessage(), e);
		}
	}

	private void printStartInfo() throws UnknownHostException {

		Environment env = applicationContext.getEnvironment();
		String serviceName = env.getProperty("spring.application.name");
		String ip = InetAddress.getLocalHost().getHostAddress();
		String protocol = Optional.ofNullable(env.getProperty("server.ssl.key-store")).map(key -> "https").orElse("http");
		String serverPort = env.getProperty("server.port");
		String socketHost = env.getProperty("socketio.host");
		String socketPort = env.getProperty("socketio.port");
		String socketPath = env.getProperty("socketio.path");
		String contextPath = Optional
				.ofNullable(env.getProperty("server.servlet.context-path"))
				.filter(StringUtils::isNotBlank)
				.orElse("/");
		String hostAddress = "localhost";
		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.warn("The host name could not be determined, using `localhost` as fallback");
		}
		log.info(
				"\n----------------------------------------------------------\n\t" +
						"Application '{}' is running! Access URLs:\n\t" +
						"Local: \t\t{}://localhost:{}{}\n\t" +
						"External: \t{}://{}:{}{}\n\t" +
						"Api doc : \t{}\n\t" +
						"WebSocket : \t{}\n\t" +
						"Profile(s): \t{}\n" +
						"\t{}\n",
				serviceName,
				protocol,
				serverPort,
				contextPath,
				protocol,
				hostAddress,
				serverPort,
				contextPath,
				"" + protocol + "://" + ip + ":8080/webjars/swagger-ui/index.html",
				FeignConst.SERVICE_USER.equals(serviceName) ? "" + protocol + "://" + socketHost + ":" + socketPort+socketPath+"/?EIO=3&transport=websocket" : "NONE",
				env.getActiveProfiles().length == 0 ? env.getDefaultProfiles() : env.getActiveProfiles(),
				"----------------------------------------------------------"
		);
	}
}
