package com.madao.article.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class Swagger3Config {

	@Bean
	public OpenAPI springShopOpenAPI() {
		Contact contact = new Contact();
		contact.setName("LGG");
		contact.setUrl("www.madaoo.com");
		contact.setEmail("1831682665@qq.com");
		return new OpenAPI()
				.info(new Info().title("MADAO-API")
						.description("如有疑问，请联系项目作者！")
						.version("v1.0")
						.contact(contact)
						.license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.components(new Components()
						.addSecuritySchemes("bearer-key",
								new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
				.externalDocs(new ExternalDocumentation()
						.description("基于SpringDoc构建")
						.url("https://springshop.wiki.github.org/docs"));
	}
}