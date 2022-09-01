package com.madao.config;

import com.madao.utils.thread.BusinessThreadExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;


@Configuration
@Slf4j
public class ThreadPoolConfig {

	/**
	 * 用户服务ForkJoinPool
	 */
	@Bean
	public ExecutorService userWorkStealingPool() {
		return BusinessThreadExecutor.buildThreadFirstExecutor("USER-ForkJoinPool");
	}

	/**
	 * 自定义CompletableFuture、parallelStream的线程池
	 * @return ExecutorService
	 */
	@Bean
	public ExecutorService newWorkStealingPool() {
		return new ForkJoinPool(Runtime.getRuntime().availableProcessors(), pool-> {
			final ForkJoinWorkerThread worker = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
			worker.setName("User-ForkJoinPool-1-worker- " + worker.getPoolIndex());
			return worker;
		},null, true);
	}
}
