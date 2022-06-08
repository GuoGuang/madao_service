package com.madao.properties;

/**
 * QQ登录配置项
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class QQProperties {

	private String providerId = "qq";
	private String clientId;
	private String clientSecret;

	public String getProviderId() {
		return providerId;
	}

	public QQProperties setProviderId(String providerId) {
		this.providerId = providerId;
		return this;
	}

	public String getClientId() {
		return clientId;
	}

	public QQProperties setClientId(String clientId) {
		this.clientId = clientId;
		return this;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public QQProperties setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
		return this;
	}
}
