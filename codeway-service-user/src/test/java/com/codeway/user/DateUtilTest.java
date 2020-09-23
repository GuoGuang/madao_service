package com.codeway.user;

import com.codeway.utils.DateUtil;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class DateUtilTest {


	@Test
	public void format_test() {
		System.out.println(DateUtil.formatNow("yyyy年MM月dd日 HH:mm:ss"));
		assertThat(DateUtil.formatNow("yyyy年MM月dd日 HH:mm:ss")).isNotNull();
	}

	@Test
	public void betweenTwoTime_test() {
		LocalDateTime start = LocalDateTime.of(1993, 10, 13, 11, 11);
		LocalDateTime end = LocalDateTime.of(1994, 11, 13, 13, 13);
		System.out.println("年:" + DateUtil.betweenTwoTime(start, end, ChronoUnit.YEARS));
		System.out.println("月:" + DateUtil.betweenTwoTime(start, end, ChronoUnit.MONTHS));
		System.out.println("日:" + DateUtil.betweenTwoTime(start, end, ChronoUnit.DAYS));
		System.out.println("半日:" + DateUtil.betweenTwoTime(start, end, ChronoUnit.HALF_DAYS));
		System.out.println("小时:" + DateUtil.betweenTwoTime(start, end, ChronoUnit.HOURS));
		System.out.println("分钟:" + DateUtil.betweenTwoTime(start, end, ChronoUnit.MINUTES));
		System.out.println("秒:" + DateUtil.betweenTwoTime(start, end, ChronoUnit.SECONDS));
		System.out.println("毫秒:" + DateUtil.betweenTwoTime(start, end, ChronoUnit.MILLIS));
		assertThat(DateUtil.betweenTwoTime(start, end, ChronoUnit.MILLIS)).isEqualTo(34221720000L);
		//=============================================================================================
        /*
                                      年:1
                                      月:13
                                      日:396
                                      半日:792
                                      小时:9506
                                      分钟:570362
                                      秒:34221720
                                      毫秒:34221720000
        */
	}

	@Test
	public void plus_test() {
		//增加二十分钟 2019年05月30日 10:53
		System.out.println(DateUtil.formatTime(DateUtil.plus(LocalDateTime.now(),20,
												ChronoUnit.MINUTES), "yyyy年MM月dd日 HH:mm"));
		//增加两年 2021年05月30日 10:33
		System.out.println(DateUtil.formatTime(DateUtil.plus(LocalDateTime.now(),2,
												ChronoUnit.YEARS), "yyyy年MM月dd日 HH:mm"));
		assertThat(DateUtil.formatTime(DateUtil.plus(LocalDateTime.now(),2,
				ChronoUnit.YEARS), "yyyy年MM月dd日 HH:mm")).isEqualTo("2021年05月30日 10:33");

	}

	@Test
	public void dayStart_test() {
		System.out.println(DateUtil.getDayStart(LocalDateTime.now()));
		System.out.println(DateUtil.getDayEnd(LocalDateTime.now()));
		assertThat(DateUtil.getDayEnd(LocalDateTime.now())).isEqualTo("2017-07-22T23:59:59.999999999");
		//=============================================================================================
        /*
                                        2017-07-22T00:00
                                2017-07-22T23:59:59.999999999
         */
	}

}