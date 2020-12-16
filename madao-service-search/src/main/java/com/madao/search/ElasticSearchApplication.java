package com.madao.search;

import com.madao.utils.DateUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@EntityScan("com.madao.model.pojo")
@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories("com.madao.user.dao")
@EnableFeignClients(basePackages = "com.madao.api")
//@EnableWebSecurity
//@EnableAdminServer
@ComponentScan(basePackages = {"com.madao"})
public class ElasticSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticSearchApplication.class, args);
	}

	@PostConstruct
	void started() {
		DateUtil.setDefaultZone();
	}

}
