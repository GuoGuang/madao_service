package com.madao.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class BusinessThreadPool {

	/**
	 * 基础业务线程池
	 */
	@Bean
	public ExecutorService fixedThreadPool(){
		return new ThreadPoolExecutor(10, 50,
				10, TimeUnit.MINUTES,
				new LinkedBlockingQueue<>(),new ThreadFactoryBuilder()
				.setNameFormat("base-fixed-pool-%d").build());
	}

	/**
	 * 耗时线程池
	 */
	// TODO

}
