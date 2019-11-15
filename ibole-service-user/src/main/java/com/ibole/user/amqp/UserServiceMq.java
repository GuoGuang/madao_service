package com.ibole.user.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * 监听队列消息，处理用户相关数据
 * @author LinGuoGuang
 * @create 2019-09-19 15:56
 **/
@Service
public class UserServiceMq {

   @RabbitListener(queues = "user")
    public void listenerFanout(Object o){
        System.out.println("监听到的消息:"+o);
    }
}
