package com.ibole.base.job;

import com.ibole.base.service.backstage.TaskService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 发送邮件Job
 * @author : LGG
 * @create : 2019-05-05
 **/
@Component
public class TestJob implements Job {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	// 如果没有自定义改写 JobFactory，这里会注入失败
	@Autowired
	private TaskService taskService;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) {
		try {
			System.out.println("持续输出中"+new Date().toString());
			//List<QuartzJob> allJobByCondition = taskService.getAllJobByCondition(new QuartzJob());
		} catch (Exception e) {
			logger.error("Parse announcement failed, error message is {}", e.getMessage());
		}
	}
}
