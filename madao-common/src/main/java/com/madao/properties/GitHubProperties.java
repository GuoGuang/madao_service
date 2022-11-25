package com.madao.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * QQ登录配置项
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Getter
@Setter
public class GitHubProperties {

	private final String providerId = "GITHUB";
	private String clientId;
	private String clientSecret;
}
