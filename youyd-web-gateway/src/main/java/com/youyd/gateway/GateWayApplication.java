package com.youyd.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @description: gateway服务网关
 * @author: LGG
 * @create: 2018-09-26 14:34
 **/

@SpringBootApplication
public class GateWayApplication {


	public static void main(String[] args) {
		SpringApplication.run(GateWayApplication.class, args);
	}


	/*@Bean
	public JWTAuthentication jwtUtil() {
		return new JWTAuthentication();
	}*/
}
