package com.ibole.properties;

/**
 * 验证码配置
 **/
public class ValidateCodeProperties {
	
	/**
	 * 图片验证码配置
	 */
	private CaptchaProperties image = new CaptchaProperties();
	/**
	 * 短信验证码配置
	 */
	private SmsCodeProperties sms = new SmsCodeProperties();

	public CaptchaProperties getImage() {
		return image; 
	}

	public void setImage(CaptchaProperties image) {
		this.image = image;
	}

	public SmsCodeProperties getSms() {
		return sms;
	}

	public void setSms(SmsCodeProperties sms) {
		this.sms = sms;
	}
	
}
