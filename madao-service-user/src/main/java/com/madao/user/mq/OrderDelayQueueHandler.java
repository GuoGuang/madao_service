package com.madao.user.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 延时队列处理器
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Component
@Slf4j
public class OrderDelayQueueHandler {
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
