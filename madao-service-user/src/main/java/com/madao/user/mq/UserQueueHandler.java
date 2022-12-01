package com.madao.user.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;

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
public class UserQueueHandler {


	/**
	 * 如果不存在，自动创建队列和交换器并且绑定
	 *
	 * @param message 消息体
	 * @param channel ack
	 */
	@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "queueOne", durable = "true", arguments = {
			@Argument(name = "x-dead-letter-exchange", value = "dlx.exchange"),
			@Argument(name = "x-dead-letter-routing-key", value = "dlx.routing.key")
	}),
			exchange = @Exchange(type = ExchangeTypes.FANOUT, value = "productLine", ignoreDeclarationExceptions = "true"), key = ""
	),containerFactory = "userMq"
	)
	public void queueOne(Message message, Channel channel) throws IOException {
		String msg = new String(message.getBody(), StandardCharsets.UTF_8);
		AtomicBoolean isSuccessful = new AtomicBoolean(false);

		try {
			log.info("队列queueOne:{}处理消息", msg);
			//  do something
		} catch (Exception e) {
			log.error("处理队列queueOne失败:{}，异常------>{}", message, e.getMessage(), e);
			isSuccessful.compareAndSet(false, true);
		}
		isACK(message, channel, isSuccessful);
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
	 *
	 * @param message      消息体
	 * @param channel      channel
	 * @param isSuccessful 是否成功
	 */
	private void isACK(Message message, Channel channel, AtomicBoolean isSuccessful) throws IOException {
		if (isSuccessful.get()) {
			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
		} else {
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		}
	}
}
