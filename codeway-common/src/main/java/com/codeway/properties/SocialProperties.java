/**
 * 
 */
package com.codeway.properties;

import lombok.Data;

/**
 * 社交登录配置项
 **/
@Data
public class SocialProperties {

	private String appId;
	private String appSecret;
	public SocialProperties() {
	}

	/**
	 * 社交登录功能拦截的url
	 */
	private String filterProcessesUrl = "/auth";

	//private QQProperties qq = new QQProperties();
	
	//private WeixinProperties weixin = new WeixinProperties();


	
}
