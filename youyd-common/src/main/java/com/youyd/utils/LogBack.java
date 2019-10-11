package com.youyd.utils;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/** https://segmentfault.com/a/1190000007214708
 * @description: LogBcak工具类
 * @author LGG
 * @create 2018-12-26 14:34
 **/
@Component
public final class LogBack {

	public static void error(String msg) {
		LoggerFactory.getLogger(getClassName()).error(msg);
	}

	public static void error(String msg, Object... obj) {
		LoggerFactory.getLogger(getClassName()).error(msg, obj);
	}


	public static void warn(String msg) {
		LoggerFactory.getLogger(getClassName()).error(msg);
	}

	public static void warn(String msg, Object... obj) {
		LoggerFactory.getLogger(getClassName()).error(msg, obj);
	}

	public static void info(String msg) {
		LoggerFactory.getLogger(getClassName()).info(msg);
	}

	public static void info(String msg, Object... obj) {
		LoggerFactory.getLogger(getClassName()).info(msg, obj);
	}

	public static void debug(String msg) {
		LoggerFactory.getLogger(getClassName()).debug(msg);
	}

	public static void debug(String msg, Object... obj) {
		LoggerFactory.getLogger(getClassName()).debug(msg, obj);
	}

	// 获取调用 error,info,debug静态类的类名
	private static String getClassName() {
		return new SecurityManager() {
			public String getClassName() {
				return getClassContext()[3].getName();
			}
		}.getClassName();
	}
}