package com.codeway.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * Security的所有配置
 **/
@Getter
@Setter
//@ConfigurationProperties(prefix = "spring.security.oauth2.client")
public class SecurityProperties {

	/**
	 * 验证码配置
	 */
	private ValidateCodeProperties code = new ValidateCodeProperties();

	/**
	 * 社交注册配置
	 */
	private RegistrationProperties registration = new RegistrationProperties();

	/**
	 * OAuth2认证服务器配置
	 */
	private OAuth2Properties oauth2 = new OAuth2Properties();


}

