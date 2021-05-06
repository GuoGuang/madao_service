package com.madao.properties;

/**
 * 验证码配置
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
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
