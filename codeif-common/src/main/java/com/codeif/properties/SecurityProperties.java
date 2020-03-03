/**
 * 
 */
package com.codeif.properties;

import lombok.Data;

/**
 * Security的所有配置
 **/
//@ConfigurationProperties(prefix = "imooc.security")
@Data
public class SecurityProperties {

	/**
	 * 验证码配置
	 */
	private ValidateCodeProperties code = new ValidateCodeProperties();
	/**
	 * 社交登录配置
	 */
	private SocialProperties social = new SocialProperties();
	/**
	 * OAuth2认证服务器配置
	 */
	private OAuth2Properties oauth2 = new OAuth2Properties();

	
}

