package com.madao.properties;

/**
 * 验证码配置
 **/
public class ValidateCodeProperties {

	/**
	 * 图片验证码配置
	 */
	private com.madao.properties.CaptchaProperties image = new com.madao.properties.CaptchaProperties();
	/**
	 * 短信验证码配置
	 */
	private com.madao.properties.SmsCodeProperties sms = new com.madao.properties.SmsCodeProperties();

	public com.madao.properties.CaptchaProperties getImage() {
		return image;
	}

	public void setImage(com.madao.properties.CaptchaProperties image) {
		this.image = image;
	}

	public com.madao.properties.SmsCodeProperties getSms() {
		return sms;
	}

	public void setSms(com.madao.properties.SmsCodeProperties sms) {
		this.sms = sms;
	}

}
