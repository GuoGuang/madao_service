package com.madaoo.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 社交登录配置项
 **/
@Getter
@Setter
public class RegistrationProperties {

	private com.madaoo.properties.GitHubProperties github = new com.madaoo.properties.GitHubProperties();

	private com.madaoo.properties.QQProperties qq = new com.madaoo.properties.QQProperties();

	/**
	 * 社交登录功能拦截的url
	 */
//	private String filterProcessesUrl = "/auth";

}
