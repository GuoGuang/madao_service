package com.madao.auth.config;

import com.madao.auth.validate.impl.ValidateCodeGenerator;
import com.madao.auth.validate.impl.captcha.CaptchaValidateCodeGenerator;
import com.madao.auth.validate.impl.sms.AliSmsCodeSender;
import com.madao.auth.validate.impl.sms.DefaultSmsCodeSender;
import com.madao.auth.validate.impl.sms.SmsCodeSender;
import com.madao.properties.SecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码相关的扩展点配置。配置在这里的bean，
 * 业务系统都可以通过声明同类型或同名的bean来覆盖安全模块默认的配置。
 */
@Configuration
public class ValidateCodeBeanConfig {
	
	private final SecurityProperties securityProperties;

	public ValidateCodeBeanConfig(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

	/**
	 * 图片验证码图片生成器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(name = "captchaValidateCodeGenerator")
	public ValidateCodeGenerator captchaValidateCodeGenerator() {
		CaptchaValidateCodeGenerator codeGenerator = new CaptchaValidateCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
	
	/**
	 * 阿里短信验证码发送器
	 */
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender aliSmsCodeSender() {
		return new AliSmsCodeSender();
	}

	/**
	 * 短信验证码发送器
	 */
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}

}
