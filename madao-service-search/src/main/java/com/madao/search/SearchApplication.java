package com.madao.search;

import com.madao.annotation.EnableSpringCloudComponent;
import com.madao.utils.DateUtil;
import org.springframework.boot.SpringApplication;

import javax.annotation.PostConstruct;

/**
 * 搜索服务
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@EnableSpringCloudComponent
public class SearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);
	}

	@PostConstruct
	void started() {
		DateUtil.setDefaultZone();
	}

}
