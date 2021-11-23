package com.madao.search.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * ES配置
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created by guoguang0536@gmail.com / 1831682775@qq.com
 * @created 2021/11/23/ 22:09:00
 */	@Configuration

public class SearchClientConfig extends AbstractElasticsearchConfiguration {

	@Value("${elasticsearch.host}")
	private String host;

	@Value("${elasticsearch.port}")
	private int port;

	@Value("${elasticsearch.username}")
	private String userName;

	@Value("${elasticsearch.password}")
	private String password;

		@Override
		@Bean
		public RestHighLevelClient elasticsearchClient() {
			final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
					.connectedTo(host+":"+port)
					.withBasicAuth(userName,password)
					.build();
			return RestClients.create(clientConfiguration).rest();
		}
	}
