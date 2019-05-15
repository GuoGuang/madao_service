package com.youyd.base;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.youyd.utils.security.JWTAuthentication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动类
 * @author : LGG
 * @create : 2018-09-26 14:34
 **/

@SpringBootApplication
@EnableEurekaClient //本服务启动后会自动注册进eureka服务中
@EnableSwagger2 // 接口文档自动生成

@EnableFeignClients(basePackages = "com.youyd.api")// 开启Feign的功能
// SpringBoot主配置类只会扫描自己所在的包及其子包下面,如果不加此扫描youyd-common里的公用包则扫描不到
@ComponentScan(basePackages = {"com.youyd"})
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

	/**
	 * JWT鉴权
	 *
	 * @return : JWTAuthentication
	 */
	@Bean
	public JWTAuthentication jwtUtil() {
		return new JWTAuthentication();
	}
	/**
	 * 分页插件
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}
}
