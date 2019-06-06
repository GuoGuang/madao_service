package com.youyd.user;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.youyd.utils.IdGenerate;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: 用户服务启动类
 *
 * @EnableEurekaClient 本服务启动后会自动注册进eureka服务中
 * @EnableSwagger2  接口文档自动生成
 * @EnableFeignClients  开启Feign的功能
 * // SpringBoot主配置类只会扫描自己所在的包及其子包下面,如果不加此扫描youyd-common里的公用包则扫描不到
 * // @ComponentScan(basePackages = {"com.youyd"})
 *
 * @author : LGG
 * @create : 2018-09-26 14:34
 **/

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EnableFeignClients(basePackages = "com.youyd.api")
@EnableWebSecurity
@EnableAdminServer
@ComponentScan(basePackages = {"com.youyd"})
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
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
	 * MP 分页插件
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

	/**
	 * 雪花算法 id生成器
	 * @return IdGenerate
	 */
	@Bean
	public IdGenerate idGenerate(){
		return new IdGenerate(1,1);
	}

}
