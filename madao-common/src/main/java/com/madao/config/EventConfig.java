package com.madao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 定义Event事件模式的线程池
 *
 * 这里的simpleApplicationEventMulticaster是spring容器中默认的multicaster。
 * 执行监听器模式的方法multicastEvent时，会看是否有指定的executor，如果没有，那么就由当前线程来invoke listener并执行subscriber的内容。
 */
@Configuration
class EventConfig {

	@Bean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
	public SimpleApplicationEventMulticaster customEventMulticaster() {
		SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster();
		simpleApplicationEventMulticaster.setTaskExecutor(eventTaskExecutor());
		return simpleApplicationEventMulticaster;
	}

	/**
	 * 定义spring所需的线程池
	 * like {@link org.springframework.context.event.SimpleApplicationEventMulticaster#multicastEvent(org.springframework.context.ApplicationEvent, org.springframework.core.ResolvableType)}
	 */
	@Bean
	public TaskExecutor eventTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(20);
		executor.setQueueCapacity(100);
		executor.setKeepAliveSeconds(300);
		executor.setThreadNamePrefix("thread-");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
		executor.setWaitForTasksToCompleteOnShutdown(true);
		return executor;
	}
}