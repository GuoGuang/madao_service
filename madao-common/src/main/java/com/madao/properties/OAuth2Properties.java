package com.madao.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * OAuth2配置项
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Getter
@Setter
public class OAuth2Properties {

	/**
	 * 客户端配置
	 */
	private OAuth2ClientProperties[] clients = {};

}
