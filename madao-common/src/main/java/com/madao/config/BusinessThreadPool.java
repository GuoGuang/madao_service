package com.madao.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

@Configuration
public class BusinessThreadPool {

	/**
	 * 普通线程池
	 */
	@Bean
	public ExecutorService fixedThreadPool() {
		return Executors.newFixedThreadPool(10, new ThreadFactoryBuilder()
				.setNameFormat("common-fixed-pool-%d").build());
	}

	/**
	 * 用户服务ForkJoinPool
	 */
	@Bean
	public static ExecutorService userWorkStealingPool() {
		return new ForkJoinPool(Runtime.getRuntime().availableProcessors(), pool -> {
			final ForkJoinWorkerThread worker = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
			worker.setName("USER-ForkJoinPool-1-worker- " + worker.getPoolIndex());
			return worker;
		}, null, true);
	}

	/**
	 * 耗时线程池
	 */
	// TODO

}