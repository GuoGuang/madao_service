package com.youyd.utils;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Map;
  
/** 定时任务管理类 
 * @date 2016-4-9
 */
public class QuartzManager {  
    private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();  //创建一个SchedulerFactory工厂实例
    private static String JOB_GROUP_NAME = "FH_JOBGROUP_NAME";  					//任务组
    private static String TRIGGER_GROUP_NAME = "FH_TRIGGERGROUP_NAME";  			//触发器组
  
    /**添加一个定时任务，使用默认的任务组名，触发器名，触发器组名  
     * @param jobName 任务名
     * @param cls 任务
     * @param time 时间设置，参考quartz说明文档
     */
    public static void addJob(String jobName, Class<? extends Job> cls, String time) {  
        try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  										//通过SchedulerFactory构建Scheduler对象
            JobDetail jobDetail= JobBuilder.newJob(cls).withIdentity(jobName,JOB_GROUP_NAME).build();	//用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
        	CronTrigger trigger = (CronTrigger) TriggerBuilder
        			.newTrigger()	 																	//创建一个新的TriggerBuilder来规范一个触发器
    				.withIdentity(jobName, TRIGGER_GROUP_NAME)											//给触发器起一个名字和组名
    				.withSchedule(CronScheduleBuilder.cronSchedule(time))
    				.build();
            sched.scheduleJob(jobDetail, trigger);  
            if (!sched.isShutdown()) {  
                sched.start();  	  // 启动  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
    
    /**添加一个定时任务，使用默认的任务组名，触发器名，触发器组名  （带参数）
     * @param jobName 任务名
     * @param cls 任务
     * @param time 时间设置，参考quartz说明文档
     */
    public static void addJob(String jobName, Class<? extends Job> cls, String time, Map<String,Object> parameter) {  
        try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  										//通过SchedulerFactory构建Scheduler对象
            JobDetail jobDetail= JobBuilder.newJob(cls).withIdentity(jobName,JOB_GROUP_NAME).build();	//用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
            jobDetail.getJobDataMap().put("parameterList", parameter);								//传参数
        	CronTrigger trigger = (CronTrigger) TriggerBuilder
        			.newTrigger()	 																	//创建一个新的TriggerBuilder来规范一个触发器
    				.withIdentity(jobName, TRIGGER_GROUP_NAME)											//给触发器起一个名字和组名
    				.withSchedule(CronScheduleBuilder.cronSchedule(time))
    				.build();
            sched.scheduleJob(jobDetail, trigger);  
            if (!sched.isShutdown()) {  
                sched.start();  	  // 启动  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    /**添加一个定时任务 
     * @param jobName	任务名 
     * @param jobGroupName	任务组名 
     * @param triggerName	触发器名 
     * @param triggerGroupName	触发器组名 
     * @param jobClass	任务 
     * @param time	时间设置，参考quartz说明文档 
     */
    public static void addJob(String jobName, String jobGroupName,  
            String triggerName, String triggerGroupName, Class<? extends Job> jobClass,  
            String time) {  
        try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  
            JobDetail jobDetail= JobBuilder.newJob(jobClass).withIdentity(jobName,jobGroupName).build();// 任务名，任务组，任务执行类
            CronTrigger trigger = (CronTrigger) TriggerBuilder	 // 触发器  
    				.newTrigger()
    				.withIdentity(triggerName, triggerGroupName)
    				.withSchedule(CronScheduleBuilder.cronSchedule(time))
    				.build();
            sched.scheduleJob(jobDetail, trigger);
            if (!sched.isShutdown()) {  
                sched.start();  	  // 启动  
            } 
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
    
    /**添加一个定时任务  （带参数）
     * @param jobName	任务名 
     * @param jobGroupName	任务组名 
     * @param triggerName	触发器名 
     * @param triggerGroupName	触发器组名 
     * @param jobClass	任务 
     * @param time	时间设置，参考quartz说明文档 
     */
    public static void addJob(String jobName, String jobGroupName,  
            String triggerName, String triggerGroupName, Class<? extends Job> jobClass,  
            String time, Map<String,Object> parameter) {  
        try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  
            JobDetail jobDetail= JobBuilder.newJob(jobClass).withIdentity(jobName,jobGroupName).build();// 任务名，任务组，任务执行类
            jobDetail.getJobDataMap().put("parameterList", parameter);								//传参数
            CronTrigger trigger = (CronTrigger) TriggerBuilder	 // 触发器  
    				.newTrigger()
    				.withIdentity(triggerName, triggerGroupName)
    				.withSchedule(CronScheduleBuilder.cronSchedule(time))
    				.build();
            sched.scheduleJob(jobDetail, trigger);
            if (!sched.isShutdown()) {  
                sched.start();  	  // 启动  
            } 
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    } 
  
    /** 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名) 
     * @param jobName	任务名 
     * @param time	新的时间设置
     */
    public static void modifyJobTime(String jobName, String time) {  
        try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  							//通过SchedulerFactory构建Scheduler对象
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName,TRIGGER_GROUP_NAME); 		//通过触发器名和组名获取TriggerKey
            CronTrigger trigger = (CronTrigger)sched.getTrigger(triggerKey);				//通过TriggerKey获取CronTrigger
            if (trigger == null) {  
                return;  
            }  
            String oldTime = trigger.getCronExpression();  
            if (!oldTime.equalsIgnoreCase(time)) {  
            	JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);						//通过任务名和组名获取JobKey
            	JobDetail jobDetail = sched.getJobDetail(jobKey); 
                Class<? extends Job> objJobClass = jobDetail.getJobClass();  
                removeJob(jobName);  
                addJob(jobName, objJobClass, time);  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
    
    /**修改一个任务的触发时间 
     * @param triggerName	任务名称
     * @param triggerGroupName	传过来的任务名称
     * @param time	更新后的时间规则
     */
    public static void modifyJobTime(String triggerName, String triggerGroupName, String time) {  
        try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  							//通过SchedulerFactory构建Scheduler对象
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName,triggerGroupName); 	//通过触发器名和组名获取TriggerKey
            CronTrigger trigger = (CronTrigger)sched.getTrigger(triggerKey);				//通过TriggerKey获取CronTrigger
            if (trigger == null)  return;  
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(trigger.getCronExpression());
            String oldTime = trigger.getCronExpression();  
            if (!oldTime.equalsIgnoreCase(time)) {  
            	trigger = (CronTrigger)trigger.getTriggerBuilder()		//重新构建trigger
            			.withIdentity(triggerKey)
            			.withSchedule(scheduleBuilder)
            			.withSchedule(CronScheduleBuilder.cronSchedule(time))
        				.build();
            	sched.rescheduleJob(triggerKey, trigger);				//按新的trigger重新设置job执行
            }
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
    
    /**移除一个任务(使用默认的任务组名，触发器名，触发器组名) 
     * @param jobName	任务名称
     */
    public static void removeJob(String jobName) {  
        try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName,TRIGGER_GROUP_NAME); 	//通过触发器名和组名获取TriggerKey
            JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);						//通过任务名和组名获取JobKey
            sched.pauseTrigger(triggerKey);	// 停止触发器  
            sched.unscheduleJob(triggerKey);// 移除触发器  
            sched.deleteJob(jobKey);		// 删除任务  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
    
    /**移除一个任务
     * @param jobName	任务名
     * @param jobGroupName	任务组名
     * @param triggerName	触发器名
     * @param triggerGroupName	触发器组名
     */
    public static void removeJob(String jobName, String jobGroupName,String triggerName, String triggerGroupName) {  
        try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName,triggerGroupName); 	//通过触发器名和组名获取TriggerKey
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);							//通过任务名和组名获取JobKey
            sched.pauseTrigger(triggerKey);	// 停止触发器  
            sched.unscheduleJob(triggerKey);// 移除触发器  
            sched.deleteJob(jobKey);		// 删除任务  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    } 
    
    /**
     * 启动所有定时任务 
     */
    public static void startJobs() {  
        try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  
            sched.start();  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    /**
     * 关闭所有定时任务 
     */
    public static void shutdownJobs() {  
        try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  
            if (!sched.isShutdown()) {  
                sched.shutdown();  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
}  