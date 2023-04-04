package com.madao.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * Security的所有配置
 * //@ConfigurationProperties(prefix = "spring.security.oauth2.client")
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Getter
@Setter
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

