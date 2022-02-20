package com.madao.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class BusinessThreadPool {

	/**
	 * 普通线程池
	 */
	@Bean
	public ExecutorService fixedThreadPool(){
		return Executors.newFixedThreadPool(10,new ThreadFactoryBuilder()
				.setNameFormat("common-fixed-pool-%d").build());
	}

	/**
	 * 耗时线程池
	 */
	// TODO

}