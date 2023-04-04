package com.madao.user.listener;

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

/**
 * 消息Fanout监听器
 * 注意：如果消息多次失败重新放回会导致队列堵塞或死循环问题 ，建议失败后进行记录，后面再补偿
 *
 * @author GuoGuang
 * @created by guoguang0536@gmail.com / 1831682775@qq.com
 * @date 2021/06/03/ 10:13:00
 */
@Slf4j
@Component
public class ReceiveConsumer {

	/**
	 * 如果不存在，自动创建队列和交换器并且绑定
	 * 处理用户相关业务
	 */
	@RabbitListener(
			bindings = @QueueBinding(
					value = @Queue(value="#{customQueueNamingStrategy.generateName(\"user\")}",durable = "true",
							arguments = {
									@Argument(name = "x-dead-letter-exchange", value = "dlx.exchange"),
									@Argument(name = "x-dead-letter-routing-key", value = "dlx.routing.key")
							}),
					exchange = @Exchange(type = ExchangeTypes.DIRECT, value = "productLine", ignoreDeclarationExceptions = "true"),
					key = ""
			), concurrency = "10-30"
	)
	public void queueOne(@Payload User user, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
		try {
			log.info("队列queueOne:{}处理消息:{}", "queueOne", user);
			//  do something
			channel.basicAck(deliveryTag, false);
		} catch (Exception e) {
			log.error("处理队列queueOne失败将信息投放到死信交换机中，异常------>{}", e.getMessage(), e);
			channel.basicNack(deliveryTag, false, false);
		}
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
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}
}