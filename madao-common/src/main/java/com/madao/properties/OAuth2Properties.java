package com.madao.properties;



/**
 * OAuth2配置项
 **/
public class OAuth2Properties {

    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};

	public OAuth2ClientProperties[] getClients() {
		return clients;
	}

	public OAuth2Properties setClients(OAuth2ClientProperties[] clients) {
		this.clients = clients;
		return this;
	}
}
