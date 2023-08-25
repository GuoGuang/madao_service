package com.madao.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * mq配置，支持连接多个不同的MQ
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Configuration
@Slf4j
@ConfigurationProperties(prefix = "spring.rabbitmq")
@ConditionalOnProperty(value = "spring.rabbitmq.host")
@Getter
@Setter
public class RabbitMQConfig {

	private String host;

	private int port;

	private String username;

	private String password;

	private boolean publisherReturns;

	private String virtualHost;

	/**
	 * 自动创建队列交换机所需要的RabbitAdmin
	 */
	@Bean
	@Primary
	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
		// 服务启动时候开启自动启动
		rabbitAdmin.setAutoStartup(true);
		return rabbitAdmin;
	}
	@Bean
	public RabbitAdmin rabbitAdminOther(ConnectionFactory connectionFactoryOther) {
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactoryOther);
		// 服务启动时候开启自动启动
		rabbitAdmin.setAutoStartup(true);
		return rabbitAdmin;
	}

	@Bean
	@Primary
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualHost);
		connectionFactory.setPublisherReturns(publisherReturns);
		connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
		return connectionFactory;
	}


	@Bean("connectionFactoryOther")
	public ConnectionFactory connectionFactoryOther() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualHost);
		connectionFactory.setPublisherReturns(publisherReturns);
		connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
		return connectionFactory;
	}

	/**
	 * 第二个MQ配置，适用于当前业务监听不同服务器的MQ
	 */
	@Bean(name="userMq")
	public SimpleRabbitListenerContainerFactory userMqRabbitFactory(ConnectionFactory connectionFactoryOther) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactoryOther);
		factory.setMessageConverter(new Jackson2JsonMessageConverter());
		// 启用观察模式，traceId传输
		factory.setObservationEnabled(true);
		factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		return factory;
	}

	@Bean
	public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(new Jackson2JsonMessageConverter());
		// 启用观察模式，traceId传输
		factory.setObservationEnabled(true);
		factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		return factory;
	}

	/**
	 * 可靠性投递配置
	 * 因为要设置回调类，所以应是prototype类型
	 *
	 * @return the amqp template
	 */
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//		rabbitTemplate.setObservationEnabled(true);
		rabbitTemplate.setEncoding("UTF-8");
		rabbitTemplate.setCorrelationDataPostProcessor((m, c) -> new CorrelationData("custom_" + System.nanoTime()));
		//开启监听回调
		rabbitTemplate.setMandatory(true);
		rabbitTemplate.setReturnsCallback(returnedMessage -> {
			log.info("------------------------------------消息成功到达exchange，但routing不到任何queue:------------------------------------");
			log.info("ReturnCallback:     消息：{}", returnedMessage.getMessage());
			log.info("ReturnCallback:     回应码：{}", returnedMessage.getReplyCode());
			log.info("ReturnCallback:     回应信息：{}", returnedMessage.getReplyText());
			log.info("ReturnCallback:     交换机：{}", returnedMessage.getExchange());
			log.info("ReturnCallback:     路由键：{}", returnedMessage.getRoutingKey());
		});
		// 消息只要被 rabbitmq broker 接收到就会执行
		rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
			log.info("------------------------------------消息成功发送到broker------------------------------------");
			log.info("ConfirmCallback:     相关数据：{}", correlationData);
			log.info("ConfirmCallback:     确认情况：{}", ack);
			log.info("ConfirmCallback:     原因：{}", cause);
			if (ack) {
				log.info("消息发送确认成功");
			} else {
				log.error("消息发送确认失败");
			}
		});
		return rabbitTemplate;
	}

}
