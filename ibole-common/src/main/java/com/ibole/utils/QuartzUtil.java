package com.ibole.utils;

import com.aliyun.oss.ServiceException;
import com.ibole.pojo.QuartzJob;
import org.quartz.*;
import org.springframework.stereotype.Component;

/**
 * 任务管理类
 **/
@Component
public class QuartzUtil {
	/**
	 * 获取 Trigger Key
	 *
	 * @param quartzJob
	 * @return
	 */
	public static TriggerKey getTriggerKey(QuartzJob quartzJob) {
		return TriggerKey.triggerKey(quartzJob.getTriggerName(), quartzJob.getTriggerGroup());
	}

	/**
	 * 获取 Job Key
	 *
	 * @param quartzJob
	 * @return
	 */
	public static JobKey getJobKey(QuartzJob quartzJob) {
		return JobKey.jobKey(quartzJob.getJobName(), quartzJob.getJobGroup());
	}

	/**
	 * 获取 Cron Trigger
	 *
	 * @param scheduler
	 * @param quartzJob
	 * @return
	 * @throws ServiceException
	 */
	public static CronTrigger getCronTrigger(Scheduler scheduler, QuartzJob quartzJob) throws ServiceException {
		try {
			return (CronTrigger) scheduler.getTrigger(getTriggerKey(quartzJob));
		} catch (SchedulerException e) {
			throw new ServiceException("Get Cron trigger failed", e);
		}
	}

	/**
	 * 创建任务
	 *
	 * @param scheduler
	 * @param quartzJob
	 * @throws ServiceException
	 */
	public static void createQuartzJob(Scheduler scheduler, QuartzJob quartzJob){

		validateCronExpression(quartzJob);

		try {
			// 要执行的 Job 的类
			Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(quartzJob.getClassName()).newInstance().getClass();

			JobDetail jobDetail = JobBuilder.newJob(jobClass)
					.withIdentity(quartzJob.getJobName(), quartzJob.getJobGroup())
					.withDescription(quartzJob.getDescription())
					.build();

			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression())
					.withMisfireHandlingInstructionDoNothing();

			CronTrigger cronTrigger = TriggerBuilder.newTrigger()
					.withIdentity(quartzJob.getTriggerName(), quartzJob.getTriggerGroup())
					.withDescription(quartzJob.getDescription())
					.withSchedule(scheduleBuilder)
					.startNow()
					.build();

			scheduler.scheduleJob(jobDetail, cronTrigger);

			LogBack.info("Create schedule job {}-{} success", quartzJob.getJobGroup(), quartzJob.getJobName());

			if (quartzJob.getPause() > 0) {
				pauseJob(scheduler, quartzJob);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogBack.error("Execute schedule job failed");
			throw new ServiceException("Execute schedule job failed", e);
		}
	}


	/**
	 * 更新任务
	 *
	 * @param scheduler
	 * @param quartzJob
	 * @throws ServiceException
	 */
	public static void updateQuartzJob(Scheduler scheduler, QuartzJob quartzJob) throws ServiceException {

		validateCronExpression(quartzJob);

		try {

			TriggerKey triggerKey = getTriggerKey(quartzJob);

			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression())
					.withMisfireHandlingInstructionDoNothing();

			CronTrigger cronTrigger = getCronTrigger(scheduler, quartzJob);

			cronTrigger = cronTrigger.getTriggerBuilder()
					.withIdentity(triggerKey)
					.withDescription(quartzJob.getDescription())
					.withSchedule(cronScheduleBuilder).build();

			scheduler.rescheduleJob(triggerKey, cronTrigger);

			LogBack.info("Update schedule job {}-{} success", quartzJob.getJobGroup(), quartzJob.getJobName());

			if (quartzJob.getPause() > 0) {
				pauseJob(scheduler, quartzJob);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			LogBack.error("Update schedule job failed");
			throw new ServiceException("Update schedule job failed", e);
		}
	}

	/**
	 * 执行任务
	 *
	 * @param scheduler
	 * @param quartzJob
	 * @throws ServiceException
	 */
	public static void run(Scheduler scheduler, QuartzJob quartzJob) throws ServiceException {
		try {
			scheduler.triggerJob(getJobKey(quartzJob));
			LogBack.info("Run schedule job {}-{} success", quartzJob.getJobGroup(), quartzJob.getJobName());
		} catch (SchedulerException e) {
			e.printStackTrace();
			LogBack.error("Run schedule job failed");
			throw new ServiceException("Run schedule job failed", e);
		}
	}

	/**
	 * 暂停任务
	 *
	 * @param scheduler
	 * @param quartzJob
	 */
	public static void pauseJob(Scheduler scheduler, QuartzJob quartzJob) throws ServiceException {
		try {
			scheduler.pauseJob(getJobKey(quartzJob));
			LogBack.info("Pause schedule job {}-{} success", quartzJob.getJobGroup(), quartzJob.getJobName());
		} catch (SchedulerException e) {
			e.printStackTrace();
			LogBack.error("Pause schedule job failed");
			throw new ServiceException("Pause job failed", e);
		}
	}

	/**
	 * 继续执行任务
	 *
	 * @param scheduler
	 * @param quartzJob
	 * @throws ServiceException
	 */
	public static void resumeJob(Scheduler scheduler, QuartzJob quartzJob) throws ServiceException {
		try {
			scheduler.resumeJob(getJobKey(quartzJob));
			LogBack.info("Resume schedule job {}-{} success", quartzJob.getJobGroup(), quartzJob.getJobName());
		} catch (SchedulerException e) {
			e.printStackTrace();
			LogBack.error("Resume schedule job failed");
			throw new ServiceException("Resume job failed", e);
		}
	}

	/**
	 * 删除任务
	 *
	 * @param scheduler
	 * @param quartzJob
	 * @throws ServiceException
	 */
	public static void deleteJob(Scheduler scheduler, QuartzJob quartzJob) throws ServiceException {
		try {
			scheduler.deleteJob(getJobKey(quartzJob));
			LogBack.info("Delete schedule job {}-{} success", quartzJob.getJobGroup(), quartzJob.getJobName());
		} catch (SchedulerException e) {
			e.printStackTrace();
			LogBack.error("Delete schedule job failed");
			throw new ServiceException("Delete job failed", e);
		}
	}

	/**
	 * 校验Cron表达式
	 */
	public static void validateCronExpression(QuartzJob quartzJob) throws ServiceException {
		if (!CronExpression.isValidExpression(quartzJob.getCronExpression())) {
			throw new ServiceException(String.format("Job %s expression %s is not correct!", quartzJob.getClassName(), quartzJob.getCronExpression()));
		}
	}

}