package com.youyd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description: 启动类
 *  @EnableEurekaClient 本服务启动后会自动注册进eureka服务中
 *  @EnableConfigServer 启用config功能
 * @author: LGG
 * @create: 2018-09-26 14:34
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
