package com.madao.task;

import com.madao.utils.thread.BusinessThreadExecutor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 公共定时任务
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2022-07-19 17:11
 */
@Slf4j
@Component
public class CommonScheduled {


	/**
	 * 查看线程池状态
	 */
	@Scheduled(cron = "0 0/1 * * * ?")
	@SchedulerLock(name = "threadPoolStatus", lockAtMostFor = "2s")
	public void threadPoolStatus() {
		Map<String, ThreadPoolExecutor> allThreadPoolExecutor = BusinessThreadExecutor.ExecutorManager.getAllThreadPoolExecutor();
		for (String poolName : allThreadPoolExecutor.keySet()) {
			BusinessThreadExecutor.displayThreadPoolStatus(allThreadPoolExecutor.get(poolName), poolName);
		}
	}
}
