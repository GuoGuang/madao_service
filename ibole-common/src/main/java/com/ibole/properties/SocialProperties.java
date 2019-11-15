/**
 * 
 */
package com.ibole.properties;

import lombok.Data;

/**
 * 社交登录配置项
 * @author : LGG
 * @create : 2019-06-18 14:34
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
