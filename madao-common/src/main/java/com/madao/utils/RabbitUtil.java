package com.madao.utils;

import com.google.common.base.Preconditions;
import com.rabbitmq.client.AMQP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ相关操作工具类
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@Component
@ConditionalOnBean(RabbitTemplate.class)
public class RabbitUtil {

	// DELAY
	public static final String DELAY_EXCHANGE = "Ex.DelayExchange";
	public static final String ORDER_USER_QUEUE = "MQ.UserDelayQueue";
	public static final String PRODUCT_ARTICLE_QUEUE = "MQ.ArticleDelayQueue";
	public static final String DELAY_USER_KEY = "delay.user";
	public static final String DELAY_ARTICLE_KEY = "delay.article";
	private final RabbitTemplate rabbitTemplate;
	public RabbitUtil(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	/**
	 * 发送广播消息
	 * RabbitMQ将会忽略第二个参数,把消息分发给所有的队列(每个队列都有消息!)
	 *
	 * @param exchangeName 交换机名称
	 * @param routingKey   发送的routingKey   （没有路由Key）
	 * @param message      内容
	 */
	public void sendFanoutToQueue(String message, String exchangeName, String routingKey) {
		rabbitTemplate.setMandatory(true);
		// 当消息成功到达exchange的时候触发的ack回调。
		rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
			log.error("correlationData:{}", correlationData);
			log.error("ack:{}", ack);
			if (!ack) {
				log.error("异常处理...");
			}
		});
		// 成功到达exchange，但routing不到任何queue时会调用
		rabbitTemplate.setReturnsCallback(returnedMessage -> log.info("MQ回调: " + returnedMessage));
		rabbitTemplate.convertAndSend(exchangeName, null, message,
				messagePostProcessor -> {
					//设置消息持久化
					messagePostProcessor.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
					messagePostProcessor.getMessageProperties().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
					return messagePostProcessor;
				});
	}

	/**
	 * 发送延时消息
	 * <p>note:delayTime不能超过2<sup>32</sup>-1</p>
	 * mqSender.sendDelay(json字符串,时间戳,RabbitMQConfig.DELAY_PRODUCT_KEY);
	 *
	 * @param message   消息
	 * @param delayTime 延时时间：毫秒
	 */
	public void sendDelay(Object message, Object delayTime) {
		//采用消息确认模式，消息发出去后，异步等待响应
		rabbitTemplate.setMandatory(true);
		rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
			log.error("correlationData:{}", correlationData);
			log.error("ack:{}", ack);
			if (!ack) {
				log.error("异常处理...");
			}
		});
		rabbitTemplate.setReturnsCallback(returnedMessage -> log.info("MQ回调: " + returnedMessage));
		//id + 时间戳 全局唯一
		CorrelationData correlationData = new CorrelationData("delay" + System.nanoTime());
		//发送消息时指定 header 延迟时间
		rabbitTemplate.convertAndSend(DELAY_EXCHANGE, DELAY_USER_KEY, message,
				messagePostProcessor -> {
					//设置消息持久化
					messagePostProcessor.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
					messagePostProcessor.getMessageProperties().setHeader("x-delay", delayTime);
					return messagePostProcessor;
				}, correlationData);
	}

	/**
	 * 获取MQ队列消息数量
	 *
	 * @param queue 队列名称
	 * @return
	 */
	public long getQueueCount(String queue) {
		AMQP.Queue.DeclareOk execute = rabbitTemplate.execute(channel -> channel.queueDeclarePassive(queue));
		Preconditions.checkNotNull(execute, "不存在的队列名称！");
		return execute.getMessageCount();
	}
}
