package com.youyd.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 日期工具类
 * @author LGG
 * @create 2019年4月20日22:35:18
 * @version 1.0.0
 */
public class DateUtil {

	/**
	 * 获取时间戳，时区加8
	 * @return ： Long
	 */
	public static Long getTimestamp(){
		return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
	}
}
