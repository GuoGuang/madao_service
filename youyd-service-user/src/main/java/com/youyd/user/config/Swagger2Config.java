package com.youyd.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>Title: Swagger2Config</p>
 * <p>Description: </p>
 * @author zhaoxd
 * @date 2018年4月23日
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    
    @Bean
    public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
    .apis(RequestHandlerSelectors.basePackage("com.youyd.user.controller")).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("项目接口API").version("0.0.1").build();
    }

}