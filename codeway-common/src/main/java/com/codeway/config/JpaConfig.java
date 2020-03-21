package com.codeway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

/**
 * JPA 审计
 */
@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "utcDateTimeProvider")
public class JpaConfig {

	/**
	 * 统一@CreatedDate 获取的时间
	 */
    @Bean
    public DateTimeProvider utcDateTimeProvider() {
    	return () -> Optional.of(LocalDateTime.now(ZoneOffset.of("+8")));
    }
}
