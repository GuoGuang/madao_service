package com.madao.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 社交登录配置项
 **/
@Getter
@Setter
public class RegistrationProperties {

	private com.madao.properties.GitHubProperties github = new com.madao.properties.GitHubProperties();

	private com.madao.properties.QQProperties qq = new com.madao.properties.QQProperties();

	/**
	 * 社交登录功能拦截的url
	 */
//	private String filterProcessesUrl = "/auth";

}
