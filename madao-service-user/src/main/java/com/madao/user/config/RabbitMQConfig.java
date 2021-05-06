package com.madao.user.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

/**
 * 延时队列配置
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }

	@Bean
	@Scope("prototype")
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMandatory(true);
		return template;
	}

    public static final String DELAY_EXCHANGE = "Ex.DelayExchange";
    public static final String ORDER_USER_QUEUE    = "MQ.UserDelayQueue";
    public static final String PRODUCT_ARTICLE_QUEUE    = "MQ.ArticleDelayQueue";
    public static final String DELAY_USER_KEY      = "delay.user";
    public static final String DELAY_ARTICLE_KEY      = "delay.article";

    /**
     * 延时交换机
     * @return TopicExchange
     */
    @Bean
    public TopicExchange delayExchange() {
        Map<String, Object> pros = new HashMap<>(3);
        //设置交换机支持延迟消息推送
        pros.put("x-delayed-message", "topic");
        TopicExchange exchange = new TopicExchange(DELAY_EXCHANGE, true, false, pros);
        //我们在也可以在 Exchange 的声明中可以设置exchange.setDelayed(true)来开启延迟队列
        exchange.setDelayed(true);
        return exchange;
    }

    /**
     * 延时队列
     * @return Queue
     */
    @Bean("userDelayQueue")
    public Queue userDelayQueue() {
        return new Queue(ORDER_USER_QUEUE, true);
    }

    @Bean("articleDelayQueue")
    public Queue articleDelayQueue() {
        return new Queue(PRODUCT_ARTICLE_QUEUE, true);
    }

    /**
     * 绑定队列和交换机,以及设定路由规则key
     *
     * @return Binding
     */
    @Bean
    public Binding delayOrderBinding() {
        return BindingBuilder.bind(userDelayQueue()).to(delayExchange()).with(DELAY_USER_KEY);
    }
    @Bean
    public Binding delayArticleBinding() {
        return BindingBuilder.bind(articleDelayQueue()).to(delayExchange()).with(DELAY_ARTICLE_KEY);
    }
}