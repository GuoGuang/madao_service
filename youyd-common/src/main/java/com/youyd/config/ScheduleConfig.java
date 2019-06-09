package com.youyd.config;

import com.youyd.utils.ScheduleJobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

/**
 *
 * https://www.jianshu.com/p/056281e057b3
 *
 */
@Configuration
@ConditionalOnBean(DataSource.class)
public class ScheduleConfig {

	@Autowired
	private ScheduleJobFactory scheduleJobFactory;

	@Bean
	@Qualifier("scheduleBean")
	public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("dataSource") DataSource dataSource) {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setSchedulerName("TASK_EXECUTOR");
		// 延迟10秒启动
//		schedulerFactoryBean.setStartupDelay(10);
		schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContextKey");
		schedulerFactoryBean.setOverwriteExistingJobs(true);
		schedulerFactoryBean.setAutoStartup(true);
		schedulerFactoryBean.setDataSource(dataSource);
		// 将 JobFactory 改为自定义的，否则在 Job 中注入 Bean 会失败
		schedulerFactoryBean.setJobFactory(scheduleJobFactory);
		return schedulerFactoryBean;
	}
}