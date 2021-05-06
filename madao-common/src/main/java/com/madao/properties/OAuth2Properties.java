package com.madao.properties;

/**
 * OAuth2配置项
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
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
