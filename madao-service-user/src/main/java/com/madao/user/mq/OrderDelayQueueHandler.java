package com.madao.user.mq;

import com.madao.user.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Component
public class OrderDelayQueueHandler {
    private final Logger log = LoggerFactory.getLogger(OrderDelayQueueHandler.class);
/*
    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private RedissonClient redisson;

    *//**
     * 处理超时用户信息
     *//*
    @RabbitListener(queues = RabbitMQConfig.ORDER_DELAY_QUEUE)
    @RabbitHandler
    public void onDelayMessage(Message msg, Channel channel) throws IOException {

        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
        String message = new String(msg.getBody(), StandardCharsets.UTF_8);

        RLock rLock = null;
        try {
            rLock = this.redisson.getLock("delay_message_user_time_out_" + message);
            if (!rLock.tryLock(0L, 300, TimeUnit.SECONDS)) {
                log.error("加锁delay_message_user_time_out超时，请求繁忙稍后重试");
            }
            log.info("延迟队列处理超时用户信息------>{}", message);

            // TODO do something

        } catch (Exception e) {
            log.error("处理超时用户信息:{}失败------>{}", message, e.getMessage(), e);
        } finally {
            channel.basicAck(deliveryTag, false);
            if (rLock != null) {
                rLock.forceUnlock();
            }
        }

    }*/
}
