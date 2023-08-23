package com.madao.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.Arrays;
/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Configuration
@Slf4j
public class CorsConfig {

	@Autowired
	Environment environment;

	@Bean
	public CorsWebFilter corsFilter() {
		log.debug("Registering CORS filter");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
		source.registerCorsConfiguration("/**", buildConfig());
		return new CorsWebFilter(source);
	}

	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		String[] activeProfiles = environment.getActiveProfiles();
		if (Arrays.asList(activeProfiles).contains("dev")) {
			corsConfiguration.addAllowedOrigin("http://localhost:9527");
			corsConfiguration.addAllowedOrigin("http://127.0.0.1:3000");
			corsConfiguration.addAllowedOrigin("http://localhost:3000");
		}
		corsConfiguration.addAllowedOrigin("http://admin.madaoo.com");
		corsConfiguration.addAllowedOrigin("http://madaoo.com");
		corsConfiguration.addAllowedOrigin("https://madaoo.com");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		return corsConfiguration;
	}
}
