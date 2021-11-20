package com.madao.annotation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("com.madao.model.entity")
@EnableFeignClients("com.madao.api")
@ComponentScan(basePackages = {"com.madao"})
@EnableCaching
public @interface EnableSpringCloudComponent {

}