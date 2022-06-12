package com.madao.user.config;

import com.madao.user.listener.FlowableBaseEventListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.springframework.amqp.rabbit.config.RabbitListenerConfigUtils;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 添加Flowable全局事件监听器
 * 配置Flowable所依赖的RabbitListenerEndpointRegistry
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2022-06-12 14:37
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class FlowableConfig implements CommandLineRunner {
	private final RuntimeService runtimeService;
	private final FlowableBaseEventListener flowableBaseEventListener;

	@Override
	public void run(String... args) {
		runtimeService.addEventListener(flowableBaseEventListener);
	}

	/*
	 * Flowable所依赖的
	 */
	@Bean(name = RabbitListenerConfigUtils.RABBIT_LISTENER_ENDPOINT_REGISTRY_BEAN_NAME)
	public RabbitListenerEndpointRegistry defaultRabbitListenerEndpointRegistry() {
		return new RabbitListenerEndpointRegistry();
	}
}
