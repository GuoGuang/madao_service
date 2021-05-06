package com.madao.properties;

/**
 * 图片验证码配置项
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class CaptchaProperties extends SmsCodeProperties {

    public CaptchaProperties() {
        setLength(4);
    }

    private int width = 124;
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
