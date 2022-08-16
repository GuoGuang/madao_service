package com.madao.config;

import cn.hutool.core.net.NetUtil;
import org.springframework.amqp.core.NamingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 自定义队列名称
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2022-08-16 23:23
 */
@Component
public class CustomQueueNamingStrategy implements NamingStrategy {
	@Value("${spring.application.name}")
	private String projectName;

	@Override
	public String generateName() {
		return null;
	}

	public String generateName(String prefix) {
		return prefix + "." + NetUtil.getLocalhostStr() + "." + projectName + ".queue";
	}
}