package com.codeway.user;

import com.codeway.utils.DateUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * EnableEurekaClient 本服务启动后会自动注册进eureka服务中
 * EnableSwagger2 接口文档自动生成
 * EnableFeignClients 开启Feign的功能
 * SpringBoot主配置类只会扫描自己所在的包及其子包下面,如果不加此扫描codeway-common里的公用包则扫描不到
 * ComponentScan(basePackages = {"com.codeway"})
 **/

@EntityScan("com.codeway.pojo")
@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories("com.codeway.user.dao")
@EnableFeignClients(basePackages = "com.codeway.api")
//@EnableWebSecurity
//@EnableAdminServer
@ComponentScan(basePackages = {"com.codeway"})
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
	 *
	 * @return : BCryptPasswordEncoder
	 */
	@Bean
	public BCryptPasswordEncoder bcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * 配置querydsl
	 *
	 * @see https://www.cnblogs.com/jpfss/p/11003964.html
	 */
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
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

}
