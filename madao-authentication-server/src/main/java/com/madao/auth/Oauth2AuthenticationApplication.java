package com.madao.auth;

import com.madao.properties.SecurityProperties;
import com.madao.utils.DateUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

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
public class Oauth2AuthenticationApplication {

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(Oauth2AuthenticationApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
	}

	@PostConstruct
	void started() {
		DateUtil.setDefaultZone();
	}

	@Bean
	public SecurityProperties securityProperties() {
		return new SecurityProperties();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public ClientDetailsService clientDetails() {
		return new JdbcClientDetailsService(dataSource);
	}

}
