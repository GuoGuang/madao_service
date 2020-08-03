package com.codeway.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 用户服务启动类
 *
 * @EnableEurekaClient 本服务启动后会自动注册进eureka服务中
 * @EnableSwagger2 接口文档自动生成
 * @EnableFeignClients 开启Feign的功能
 * // SpringBoot主配置类只会扫描自己所在的包及其子包下面,如果不加此扫描codeway-common里的公用包则扫描不到
 * // @ComponentScan(basePackages = {"com.codeway"})
 **/
@SpringBootApplication
@EntityScan("com.codeway.pojo")
@EnableEurekaClient
@EnableJpaRepositories("com.codeway.article.dao")
@EnableFeignClients("com.codeway.api")
@ComponentScan(basePackages = {"com.codeway"})
public class ArticleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
	}
}
