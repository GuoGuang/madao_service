package com.madao.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.*;

import java.util.Optional;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@RestController
public class SwaggerHandler {

	@Autowired(required = false)
	private SecurityConfiguration securityConfiguration;

	@Autowired(required = false)
	private UiConfiguration uiConfiguration;

	private final SwaggerResourcesProvider swaggerResources;

	@Autowired
	public SwaggerHandler(SwaggerResourcesProvider swaggerResources) {
		this.swaggerResources = swaggerResources;
	}

	/**
	 * Swagger安全配置，支持oauth和apiKey设置
	 */
	@GetMapping("/swagger-resources/configuration/security")
	public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration() {
		return Mono.just(new ResponseEntity<>(
				Optional.ofNullable(securityConfiguration).orElse(SecurityConfigurationBuilder.builder().build()), HttpStatus.OK));
	}

	/**
	 * Swagger UI配置
	 */
	@GetMapping("/swagger-resources/configuration/ui")
	public Mono<ResponseEntity<UiConfiguration>> uiConfiguration() {
		return Mono.just(new ResponseEntity<>(
				Optional.ofNullable(uiConfiguration).orElse(UiConfigurationBuilder.builder().build()), HttpStatus.OK));
	}

	/**
	 * Swagger资源配置，微服务中这各个服务的api-docs信息
	 */
	@GetMapping("/swagger-resources")
	public Mono<ResponseEntity> swaggerResources() {
		return Mono.just((new ResponseEntity<>(swaggerResources.get(), HttpStatus.OK)));
	}
}