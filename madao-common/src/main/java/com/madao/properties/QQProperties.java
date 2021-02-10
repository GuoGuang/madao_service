package com.madao.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * QQ登录配置项
 **/
@Getter
@Setter
public class QQProperties {

    private String providerId = "qq";
    private String clientId;
    private String clientSecret;

}
