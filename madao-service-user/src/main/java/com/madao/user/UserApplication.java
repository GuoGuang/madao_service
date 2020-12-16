package com.madao.user;

import com.madao.utils.DateUtil;
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

@EntityScan("com.madao.model.pojo")
@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories("com.madao.user.dao")
@EnableFeignClients(basePackages = "com.madao.api")
//@EnableWebSecurity
//@EnableAdminServer
@ComponentScan(basePackages = {"com.madao"})
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
	 * @return : BCryptPasswordEncoder
	 */
	@Bean
	public BCryptPasswordEncoder bcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * 配置querydsl
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
