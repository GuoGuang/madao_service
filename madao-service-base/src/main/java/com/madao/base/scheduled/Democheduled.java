package com.madao.base.scheduled;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2022-02-10 18:27
 */
@Slf4j
@Component
public class Democheduled {

	@Scheduled(cron = "0 0/10 * * * ?")
	@SchedulerLock(name = "getQueueCount", lockAtMostFor = "6s", lockAtLeastFor = "3s")
	public void test() {

	}
}
