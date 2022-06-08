package com.madao.base.scheduled;

import com.madao.base.dao.MqMonitorDao;
import com.madao.model.entity.MqMonitor;
import com.madao.utils.RabbitUtil;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 定时任务监控MQ消息数量并告警
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2022-02-10 18:27
 */
@Slf4j
@Component
public class MqMonitorScheduled {

	@Autowired(required = false)
	private RabbitUtil rabbitUtil;
	@Autowired
	private MqMonitorDao mqMonitorDao;

	/**
	 * 每十分钟获取一次队列中的消息数量，根据阈值判断是否告警
	 */
	@Scheduled(cron = "0 0/10 * * * ?")
	@SchedulerLock(name = "getQueueCount", lockAtMostFor = "6s", lockAtLeastFor = "3s")
	public void getQueueCount() {
		List<MqMonitor> allQueue = mqMonitorDao.findAll();
		for (MqMonitor mqMonitor : allQueue) {
			long queueCount = rabbitUtil.getQueueCount(mqMonitor.getQueue());
			if (queueCount > mqMonitor.getThreshold()) {
				log.error("MQ队列：{}超过所设置的阈值：{}！", mqMonitor.getQueue(), mqMonitor.getThreshold());
			}
		}
	}
}
