package com.youyd.base;

import com.aliyun.oss.ServiceException;
import com.youyd.base.service.TaskService;
import com.youyd.pojo.QuartzJob;
import com.youyd.utils.QuartzUtil;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationListener implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private TaskService taskService;

	@Autowired
	private Scheduler scheduler;

	@Override
	public void run(String... args) throws Exception {

		// 应用启动之后执行所有可执行的的任务
		QuartzJob quartzJob = new QuartzJob();
		quartzJob.setEnable(1);
		List<QuartzJob> scheduleJobList = taskService.getAllJobByCondition(quartzJob);
		for (QuartzJob scheduleJob : scheduleJobList) {
			try {
				CronTrigger cronTrigger = QuartzUtil.getCronTrigger(scheduler, scheduleJob);
				if (cronTrigger == null) {
					QuartzUtil.createQuartzJob(scheduler, scheduleJob);
				} else {
					QuartzUtil.updateQuartzJob(scheduler, scheduleJob);
				}
				logger.info("Startup {}-{} success", scheduleJob.getJobGroup(), scheduleJob.getJobName());
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}
}

