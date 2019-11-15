package com.ibole.authorization;

import com.ibole.utils.IdGenerate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


/**
 * 鉴权服务启动类
 * @author : LGG
 * @create : 2019-06-13
 **/
@SpringBootApplication
@EnableFeignClients(basePackages = "com.ibole.api")
@ComponentScan(basePackages = {"com.ibole"})
@EnableDiscoveryClient
@EnableRabbit // 启用RabbitMQ
public class Oauth2AuthorizationApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2AuthorizationApplication.class, args);
    }
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
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
