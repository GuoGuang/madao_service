package com.youyd.base.service;

import com.youyd.pojo.QuartzJob;
import com.youyd.utils.QuartzUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * 测试Quart任务调度功能
 * https://blog.csdn.net/u013360850/article/details/79318343
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest{

	@Autowired
	TaskService taskService;


	@Autowired
	private Scheduler scheduler;
	@Test
	public void te() throws SchedulerException {
		//boolean resume = taskService.resume("1");

		QuartzJob quartzJob = new QuartzJob();
		quartzJob.setEnable(1);
		List<QuartzJob> scheduleJobList = taskService.getAllJobByCondition(quartzJob);
		QuartzUtil.createQuartzJob(scheduler, scheduleJobList.get(0));

		System.out.println("");
	}

}