package com.madao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * \@EnableScheduling默认线程池大小为1，如果部分任务出现执行时间过长的情况时会阻塞其他定时任务的执行。
 * 解决方式分两种：
 * 1、配置TaskScheduler类指定该注解默认线程池大小
 * 2、使用@Async注解，默认线程池100
 */
@Configuration
public class ScheduleConfig {

	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		//设定最大可用的线程数目
		taskScheduler.setPoolSize(10);
		taskScheduler.setThreadNamePrefix("task-custom");
		return taskScheduler;
	}
}
