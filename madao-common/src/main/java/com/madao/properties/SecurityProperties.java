package com.madao.properties;

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
    private com.madao.properties.ValidateCodeProperties code = new com.madao.properties.ValidateCodeProperties();

    /**
     * 社交注册配置
     */
    private com.madao.properties.RegistrationProperties registration = new com.madao.properties.RegistrationProperties();

    /**
     * OAuth2认证服务器配置
     */
    private com.madao.properties.OAuth2Properties oauth2 = new com.madao.properties.OAuth2Properties();


}

