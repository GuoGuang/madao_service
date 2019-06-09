package com.youyd.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @description: gateway服务网关
 * @author: LGG
 * @create: 2018-09-26 14:34
 **/

@ComponentScan(basePackages = {"com.youyd"})
@SpringBootApplication()
public class GateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateWayApplication.class, args);
	}

}
