package com.madaoo.properties;

import com.madaoo.constant.CommonConst;
import lombok.Getter;
import lombok.Setter;

/**
 * 短信验证码配置
 */
@Getter
@Setter
public class SmsCodeProperties {
	
	/**
	 * 验证码长度
	 */
	private int length = 6;
	/**
	 * 过期时间
	 */
	private int expireIn = CommonConst.TIME_OUT_HALF_HOUR;
	/**
	 * 要拦截的url，多个url用逗号隔开，ant pattern
	 */
	private String url;
}
