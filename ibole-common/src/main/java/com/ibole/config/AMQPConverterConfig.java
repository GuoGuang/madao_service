package com.ibole.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义rabbitMQ序列化转换器
 * @author  GuoGuang
 **/

@Configuration
public class AMQPConverterConfig {

    @Bean
    public MessageConverter messageConverter (){
        return  new Jackson2JsonMessageConverter();
    }
}
