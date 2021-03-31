package com.madao.user.mq;

import com.madao.user.config.RabbitMQConfig;
import com.madao.utils.LogBack;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * MQ发送工具类
 */
@Component
public class MQSender {

    private final RabbitTemplate rabbitTemplate;

	public MQSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

    final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
	    LogBack.error("correlationData:{}",correlationData);
	    LogBack.error("ack:{}",ack);
        if (!ack) {
        	LogBack.error("异常处理...");
        }
    };

    final RabbitTemplate.ReturnCallback returnCallback = (message, replyCode, replyText, exchange, routingKey) ->
		    LogBack.info("return exchange:{},routingKey:{}, replyCode:{}, replyText: {} ",exchange,routingKey,replyCode,replyText);

	/**
	 * 发送延时消息
	 * @param message 消息
	 * @param delayTime 延时时间：毫秒
	 */
	public void sendDelay(Object message, Object delayTime) {
        //采用消息确认模式，消息发出去后，异步等待响应
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        //id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData("delay" + System.nanoTime());
        //发送消息时指定 header 延迟时间
        rabbitTemplate.convertAndSend(RabbitMQConfig.DELAY_EXCHANGE, RabbitMQConfig.DELAY_USER_KEY, message,
                message1 -> {
                    //设置消息持久化
                    message1.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    message1.getMessageProperties().setHeader("x-delay", delayTime);
                    return message1;
                }, correlationData);
    }
}
