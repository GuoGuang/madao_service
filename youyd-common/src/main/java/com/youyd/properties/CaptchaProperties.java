/**
 * 
 */
package com.youyd.properties;

/**
 * 图片验证码配置项
 * @author : LGG
 * @create : 2019-06-18 14:34
 **/
public class CaptchaProperties extends SmsCodeProperties {
	
	public CaptchaProperties() {
		setLength(4);
	}
	
	/**
	 * 图片宽
	 */
	private int width = 124;
	/**
	 * 图片高
	 */
	private int height = 30;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

}
