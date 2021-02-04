package com.madao.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Profile({"dev"})
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
	    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
			    .apis(RequestHandlerSelectors.basePackage("com.madao.gateway.controller")).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("网关API")
            .description("如有疑问，请联系项目作者。")
            .contact(new Contact("MADAO", "https://madaoo.com", "1831682775@qq.com"))
            .version("6.2.3").build();
    }

}