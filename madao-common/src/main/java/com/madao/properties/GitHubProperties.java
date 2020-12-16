package com.madao.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * QQ登录配置项
 **/
@Getter
@Setter
public class GitHubProperties {

	private final String providerId = "GITHUB";
	private String clientId;
	private String clientSecret;

}
