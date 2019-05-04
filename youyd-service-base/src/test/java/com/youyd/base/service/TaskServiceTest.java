package com.youyd.base.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 测试Quart任务调度功能
 * https://blog.csdn.net/u013360850/article/details/79318343
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest{

	@Autowired
	TaskService taskService;

	@Test
	public void te(){
		boolean resume = taskService.resume("1124678185533489154");
		System.out.println(resume);
	}

}