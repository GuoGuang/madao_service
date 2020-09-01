package com.codeway.search;

import com.codeway.utils.DateUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@EntityScan("com.codeway.model.pojo")
@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories("com.codeway.user.dao")
@EnableFeignClients(basePackages = "com.codeway.api")
//@EnableWebSecurity
//@EnableAdminServer
@ComponentScan(basePackages = {"com.codeway"})
public class ElasticSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticSearchApplication.class, args);
	}

	@PostConstruct
	void started() {
		DateUtil.setDefaultZone();
	}

}
