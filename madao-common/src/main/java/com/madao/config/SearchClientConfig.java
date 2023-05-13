package com.madao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

/**
 * ES配置
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created by guoguang0536@gmail.com / 1831682775@qq.com
 * @created 2021/11/23/ 22:09:00
 */
@Configuration
@ConditionalOnProperty(value = "elasticsearch.host")
public class SearchClientConfig extends ElasticsearchConfiguration {

	@Value("${elasticsearch.host}")
	private String host;

	@Value("${elasticsearch.port}")
	private int port;

	@Value("${elasticsearch.username}")
	private String userName;

	@Value("${elasticsearch.password}")
	private String password;

	@Override
	public ClientConfiguration clientConfiguration() {
		return ClientConfiguration.builder()
				.connectedTo(host + ":" + port)
				.withBasicAuth(userName, password)
				.build();
	}
}
