package com.madao.constant;

public final class CommonConst {

	public static final int ADD = 1;
	public static final int DELETE= 2;
	public static final int MODIFY= 3;

	/*超时时间*/
	// 五分钟
	public static final Integer TIME_OUT_FIVE_MINUTES = 60 * 5;
	// 半小时
	public static final Integer TIME_OUT_HALF_HOUR = 60 * 30;
	// 三个月
	public static final Integer TIME_OUT_THREE_MONTHS = 60 * 60 * 24 * 30 * 3;
	// 一周
	public static final Integer TIME_OUT_WEEK = 60 * 60 * 24 * 7;
	// 一天
	public static final Integer TIME_OUT_DAY = 60 * 60 * 24;

	// 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
	public static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "captcha";
	public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

	// 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
	public static final String DEFAULT_PARAMETER_NAME_PHONE = "phone";

	private CommonConst(){}
}
