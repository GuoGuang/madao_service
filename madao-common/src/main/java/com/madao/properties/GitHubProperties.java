package com.madao.properties;

/**
 * QQ登录配置项
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class GitHubProperties {

    private final String providerId = "GITHUB";
    private String clientId;
    private String clientSecret;

	public String getProviderId() {
		return providerId;
	}

	public String getClientId() {
		return clientId;
	}

	public GitHubProperties setClientId(String clientId) {
		this.clientId = clientId;
		return this;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public GitHubProperties setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
		return this;
	}
}
