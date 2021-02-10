package com.madao.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * OAuth2配置项
 **/
@Getter
@Setter
public class OAuth2Properties {

    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};

}
