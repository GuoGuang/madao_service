package com.ibole.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ibole.annotation.CronExpress;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Quartz 任务调度
 **/
@Getter
@Setter
@TableName("ba_job")
public class QuartzJob extends BasePojo implements Serializable {

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;

	@NotNull(message = "类名不能为空")
	@Pattern(regexp = "([a-zA-Z0-9\u4E00-\u9FA5]{2,10})", message = "必须是2到10位(字母，数字)名称！")
	private String className; // 任务执行时调用哪个类的方法 包名+类名，完全限定名

	@NotNull(message = "CRON 表达式不能为空")
	@CronExpress(message = "测试正则 ")
	private String cronExpression; // cron 表达式

	@NotNull(message="任务名称不能为空")
	@Pattern(regexp="([a-zA-Z\u4E00-\u9FA5]{2,10})",message="必须是2到10位长度名称！")
	private String jobName; // 任务名称

	private String jobGroup; // 任务组

	private String triggerName; // 触发器名称

	private String triggerGroup; // 触发组

	private Integer pause; // 暂停

	private Integer enable; // 是否启用

	@NotNull(message="描述不能为空")
	@Pattern(regexp="([a-zA-Z0-9\u4E00-\u9FA5]{2,10})",message="至少是2到10位长度描述！")
	private String description; // 描述

	private String jobId; // 任务id


}

