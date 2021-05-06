package com.madao.properties;

import com.madao.constant.CommonConst;

/**
 * 短信验证码配置
 */
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

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
