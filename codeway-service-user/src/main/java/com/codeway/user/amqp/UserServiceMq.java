package com.codeway.user.amqp;

import org.springframework.stereotype.Service;

/**
 * 监听队列消息，处理用户相关数据
 * @author LinGuoGuang
 **/
@Service
public class UserServiceMq {

  /* @RabbitListener(queues = "user")
    public void listenerFanout(Object o){
        System.out.println("监听到的消息:"+o);
    }*/
}
