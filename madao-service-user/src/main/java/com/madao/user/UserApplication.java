package com.madao.user;

import com.madao.annotation.EnableSpringCloudComponent;
import com.madao.config.chain.AbstractCommonHandler;
import com.madao.model.dto.user.UserDto;
import com.madao.user.config.chain.CreditHandler;
import com.madao.user.config.chain.WindControlHandler;
import com.madao.utils.DateUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.springframework.amqp.rabbit.config.RabbitListenerConfigUtils;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 用户服务
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@EnableSpringCloudComponent
@EnableJpaRepositories("com.madao.user.dao")
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@PostConstruct
	void started() {
		DateUtil.setDefaultZone();
	}

	/**
	 * BCrypt加密算法
	 */
	@Bean
	public BCryptPasswordEncoder bcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


	/**
	 * 配置querydsl
	 */
	@Bean
	public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
		return new JPAQueryFactory(entityManager);
	}

	/**
	 * 注册的新用户校验
	 * 1、满足风控条件
	 * 2、满足中国公民条件
	 * 3、满足引用条件
	 */
	@Bean
	public AbstractCommonHandler<UserDto> userSaveHandler(){
		AbstractCommonHandler.Builder<UserDto> builder = new AbstractCommonHandler.Builder<>();
		return builder.addHandler(new WindControlHandler())
				.addHandler(new CreditHandler())
				.build();
	}

	/**
	 * 加密的配置文件信息
	 */
	/*@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
		yaml.setResources(new ClassPathResource("password.yml"));
		configurer.setProperties(yaml.getObject());
		return configurer;
	}*/

	/*
	 * Flowable所依赖的
	 */
	@Bean(name = RabbitListenerConfigUtils.RABBIT_LISTENER_ENDPOINT_REGISTRY_BEAN_NAME)
	public RabbitListenerEndpointRegistry defaultRabbitListenerEndpointRegistry() {
		return new RabbitListenerEndpointRegistry();
	}

}
