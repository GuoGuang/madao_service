package com.youyd.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Quartz 任务调度
 * @author : LGG
 * @create : 28-February-2019
 **/
@Getter
@Setter
@TableName("us_job")
public class QuartzJob extends BasePojo implements Serializable {

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;

	private String className; // 任务执行时调用哪个类的方法 包名+类名，完全限定名

	private String cronExpression; // cron 表达式

	private String jobName; // 任务名称

	private String jobGroup; // 任务组

	private String triggerName; // 触发器名称

	private String triggerGroup; // 触发组

	private Integer pause; // 暂停

	private Integer enable; // 是否启用

	private String description; // 描述

	private String jobId; // 任务id


}

