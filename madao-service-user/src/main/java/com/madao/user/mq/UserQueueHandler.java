package com.madao.user.mq;

import com.madao.model.entity.user.User;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * 处理用户队列
 * 以注解方式绑定交换机和队列
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 */
@Slf4j
@Component
public class UserQueueHandler {

	/**
	 * 如果不存在，自动创建队列和交换器并且绑定
	 */
	@RabbitListener(bindings = @QueueBinding(value = @Queue(value="#{customQueueNamingStrategy.generateName(\"user\")}", durable = "true", arguments = {
			@Argument(name = "x-dead-letter-exchange", value = "dlx.exchange"),
			@Argument(name = "x-dead-letter-routing-key", value = "dlx.routing.key")
	}),
			exchange = @Exchange(type = ExchangeTypes.FANOUT, value = "productLine", ignoreDeclarationExceptions = "true"), key = ""
	),containerFactory = "userMq",concurrency = "10-30"
	)
	public void queueOne(@Payload User user, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
		AtomicBoolean isSuccessful = new AtomicBoolean(false);
		try {
			log.info("队列queueOne:{}处理消息", user);
			//  do something
		} catch (Exception e) {
			log.error("处理队列queueOne失败，异常------>{}", e.getMessage(), e);
			isSuccessful.compareAndSet(false, true);
		}
		isACK(deliveryTag, channel, isSuccessful);
	}

	@RabbitListener(
			bindings = @QueueBinding(
					value = @Queue(value = "dead", durable = "true"),
					exchange = @Exchange(value = "dlx.exchange"),
					key = "dlx.routing.key"
			)
	)
	public void deadHandler(Message message, Channel channel) throws IOException {
		String msg = new String(message.getBody(), StandardCharsets.UTF_8);

		log.info("死信队列 message={}", msg);
		// 记录异常消息，手工补偿
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}

	/**
	 * 标记是否成功处理消息
	 * @param isSuccessful 是否成功
	 */
	private void isACK(long deliveryTag, Channel channel, AtomicBoolean isSuccessful) throws IOException {
		if (isSuccessful.get()) {
			// 丢弃消息
			channel.basicNack(deliveryTag, false, false);
		} else {
			// 消息确认
			// 通知服务器此消息已经被消费，可从队列删掉， 这样以后就不会重发，否则后续还会在发
			channel.basicAck(deliveryTag, false);
		}
	}
}
