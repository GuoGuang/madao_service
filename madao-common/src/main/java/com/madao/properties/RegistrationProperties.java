package com.madao.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 社交登录配置项
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Getter
@Setter
public class RegistrationProperties {

	private GitHubProperties github = new GitHubProperties();

	private QQProperties qq = new QQProperties();

	/**
	 * 社交登录功能拦截的url
	 */
//	private String filterProcessesUrl = "/auth";
}
