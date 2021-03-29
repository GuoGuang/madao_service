package com.madao.properties;



/**
 * Security的所有配置
 **/
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

	public ValidateCodeProperties getCode() {
		return code;
	}

	public SecurityProperties setCode(ValidateCodeProperties code) {
		this.code = code;
		return this;
	}

	public RegistrationProperties getRegistration() {
		return registration;
	}

	public SecurityProperties setRegistration(RegistrationProperties registration) {
		this.registration = registration;
		return this;
	}

	public OAuth2Properties getOauth2() {
		return oauth2;
	}

	public SecurityProperties setOauth2(OAuth2Properties oauth2) {
		this.oauth2 = oauth2;
		return this;
	}
}

