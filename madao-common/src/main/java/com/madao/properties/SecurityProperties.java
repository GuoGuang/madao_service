package com.madao.properties;



/**
 * Security的所有配置
 **/
//@ConfigurationProperties(prefix = "spring.security.oauth2.client")
public class SecurityProperties {

    /**
     * 验证码配置
     */
    private com.madao.properties.ValidateCodeProperties code = new com.madao.properties.ValidateCodeProperties();

    /**
     * 社交注册配置
     */
    private com.madao.properties.RegistrationProperties registration = new com.madao.properties.RegistrationProperties();

    /**
     * OAuth2认证服务器配置
     */
    private com.madao.properties.OAuth2Properties oauth2 = new com.madao.properties.OAuth2Properties();

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

