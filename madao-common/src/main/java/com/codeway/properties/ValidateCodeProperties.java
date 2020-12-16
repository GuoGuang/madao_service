package com.madaoo.properties;

/**
 * 验证码配置
 **/
public class ValidateCodeProperties {

	/**
	 * 图片验证码配置
	 */
	private com.madaoo.properties.CaptchaProperties image = new com.madaoo.properties.CaptchaProperties();
	/**
	 * 短信验证码配置
	 */
	private com.madaoo.properties.SmsCodeProperties sms = new com.madaoo.properties.SmsCodeProperties();

	public com.madaoo.properties.CaptchaProperties getImage() {
		return image;
	}

	public void setImage(com.madaoo.properties.CaptchaProperties image) {
		this.image = image;
	}

	public com.madaoo.properties.SmsCodeProperties getSms() {
		return sms;
	}

	public void setSms(com.madaoo.properties.SmsCodeProperties sms) {
		this.sms = sms;
	}

}
