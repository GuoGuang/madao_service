package com.codeway.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 搜索服务启动类
 **/

@SpringBootApplication
@EnableEurekaClient //本服务启动后会自动注册进eureka服务中
@EnableSwagger2 // 接口文档自动生成
@EnableFeignClients // 开启Feign的功能
// SpringBoot主配置类只会扫描自己所在的包及其子包下面,如果不加此扫描codeway-common里的公用包则扫描不到
@ComponentScan(basePackages = {"com.codeway"})
public class ElasticSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchApplication.class, args);
    }

}
