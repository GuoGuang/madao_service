package com.madao.auth;

import com.madao.config.BasicApplication;
import com.madao.properties.SecurityProperties;
import jakarta.servlet.Filter;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Oauth2授权、鉴权
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2018-01-12 9:01
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.madao.api")
@ComponentScan(basePackages = {"com.madao"})
@EnableDiscoveryClient
@EnableRabbit
public class Oauth2AuthenticationApplication extends BasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2AuthenticationApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(SecurityFilterChain securityFilterChain) {
		return args -> {
			for (int i = 0; i < securityFilterChain.getFilters().size(); i++) {
				Filter filter = securityFilterChain.getFilters().get(i);
				System.out.println("第" + i + "个过滤器：" + filter.getClass().getName());
			}
		};
	}

	@Bean
	public SecurityProperties securityProperties() {
		return new SecurityProperties();
	}

}
