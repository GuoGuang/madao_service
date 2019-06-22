package com.youyd.constant;


/**
 * 公共模块常量
 * @author LGG
 * @create 2019年4月23日22:35:18
 * @version 1.0.0
 */
public interface CommonConst {

	int ADD = 1;
	int DELETE= 2;
	int MODIFY= 3;

	/*超时时间*/
	// 半小时
	Integer TIME_OUT_HALF_HOUR = 60 * 30;
	// 三个月
	Integer TIME_OUT_THREE_MONTHS = 60 * 60 * 24 * 30 * 3;
	// 一周
	Integer TIME_OUT_WEEK = 60 * 60 * 24 * 7;
	// 一天
	Integer TIME_OUT_DAY = 60 * 60 * 24;

	/**
	 * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
	 */
	String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "captcha";
	/**
	 * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
	 */
	String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
	/**
	 * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
	 */
	String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";
}
