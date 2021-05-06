package com.madao.gateway.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomHttpMessageConverters {

    /**
     * fix feign.codec.DecodeException:
     * No qualifying bean of type
     * 'org.springframework.boot.autoconfigure.http.HttpMessageConverters'
     * available: expected at least 1 bean which qualifies as autowire candidate.
     * Dependency annotations:
     * {@org.springframework.beans.factory.annotation.Autowired(required=true)}
     */
    @Bean
    public HttpMessageConverters customConverters() {
        return new HttpMessageConverters();
    }
}
