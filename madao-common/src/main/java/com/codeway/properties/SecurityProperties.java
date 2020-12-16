package com.madaoo.properties;

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
	private com.madaoo.properties.ValidateCodeProperties code = new com.madaoo.properties.ValidateCodeProperties();

	/**
	 * 社交注册配置
	 */
	private com.madaoo.properties.RegistrationProperties registration = new com.madaoo.properties.RegistrationProperties();

	/**
	 * OAuth2认证服务器配置
	 */
	private com.madaoo.properties.OAuth2Properties oauth2 = new com.madaoo.properties.OAuth2Properties();


}

