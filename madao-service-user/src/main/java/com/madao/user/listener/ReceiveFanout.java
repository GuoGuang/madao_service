package com.madao.user.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 消息Fanout监听器
 * 注意：如果消息多次失败重新放回会导致队列堵塞或死循环问题 ，建议失败后进行记录，后面再补偿
 * @author GuoGuang
 * @created by guoguang0536@gmail.com / 1831682775@qq.com
 * @date 2021/06/03/ 10:13:00
 */
@Slf4j
@Component
public class ReceiveFanout {

    /**
     * 如果不存在，自动创建队列和交换器并且绑定
     * @param message
     * @param channel ack
     */
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "queueOne", durable = "true",
                            arguments = {
                                    @Argument(name = "x-dead-letter-exchange", value = "dlx.exchange"),
                                    @Argument(name = "x-dead-letter-routing-key", value = "dlx.routing.key")
                            }),
                    exchange = @Exchange(type = ExchangeTypes.FANOUT, value = "productLine", ignoreDeclarationExceptions = "true"),
                    key = ""
            )
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
                    value = @Queue(value = "queueThree", durable = "true",
                            arguments = {
                                    @Argument(name = "x-dead-letter-exchange", value = "dlx.exchange"),
                                    @Argument(name = "x-dead-letter-routing-key", value = "dlx.routing.key")
                            }),
                    exchange = @Exchange(type = ExchangeTypes.FANOUT, value = "productLine", ignoreDeclarationExceptions = "true"),
                    key = ""
            )
    )
    public void queueThree(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);

        try {
            log.info("队列queueThree:{}处理消息", msg);
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            throw new RuntimeException();
            //  do something
        } catch (Exception e) {
            log.error("处理队列queueThree失败:{}，异常------>{}", message, e.getMessage(), e);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
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

    private void isACK(Message message, Channel channel, AtomicBoolean isSuccessful) throws IOException {
        if (isSuccessful.get()) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        } else {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}