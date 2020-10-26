package com.codeway.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.Arrays;

@Configuration
public class CorsConfig {

	@Autowired
	Environment environment;

	@Bean
	public CorsWebFilter corsFilter() {
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
		}
		corsConfiguration.addAllowedOrigin("http://admin.codeway.fun");
		corsConfiguration.addAllowedOrigin("http://codeway.me");
		corsConfiguration.addAllowedOrigin("https://codeway.me");
		corsConfiguration.addAllowedOrigin("http://codeway.fun");
		corsConfiguration.addAllowedOrigin("https://codeway.fun");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		return corsConfiguration;
	}
}
