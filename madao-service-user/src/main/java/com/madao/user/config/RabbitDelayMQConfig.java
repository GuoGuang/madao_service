package com.madao.user.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


/**
 * 延时队列配置
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Configuration
@ConditionalOnBean(RabbitTemplate.class)
public class RabbitDelayMQConfig {

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
/*
    *  使用注解@RabbitListener(bindings = @QueueBinding(替换
    *//**
     * 延时交换机
     * @return TopicExchange
     *//*
    @Bean
    public TopicExchange delayExchange() {
        Map<String, Object> pros = new HashMap<>(3);
        //设置交换机支持延迟消息推送
//        pros.put("x-delayed-message", "topic");
        TopicExchange exchange = new TopicExchange(RabbitUtil.DELAY_EXCHANGE, true, false, pros);
        //我们在也可以在 Exchange 的声明中可以设置exchange.setDelayed(true)来开启延迟队列
        exchange.setDelayed(true);
        return exchange;
    }

    *//**
     * 延时队列
     * @return Queue
     *//*
    @Bean("userDelayQueue")
    public Queue userDelayQueue() {
        return new Queue(RabbitUtil.ORDER_USER_QUEUE, true);
    }

    @Bean("articleDelayQueue")
    public Queue articleDelayQueue() {
        return new Queue(RabbitUtil.PRODUCT_ARTICLE_QUEUE, true);
    }

    *//**
     * 绑定队列和交换机,以及设定路由规则key
     *
     * @return Binding
     *//*
    @Bean
    public Binding delayOrderBinding() {
        return BindingBuilder.bind(userDelayQueue()).to(delayExchange()).with(RabbitUtil.DELAY_USER_KEY);
    }
    @Bean
    public Binding delayArticleBinding() {
        return BindingBuilder.bind(articleDelayQueue()).to(delayExchange()).with(RabbitUtil.DELAY_ARTICLE_KEY);
    }*/
}