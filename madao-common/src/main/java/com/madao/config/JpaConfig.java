package com.madao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

/**
 * JPA 审计
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
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
