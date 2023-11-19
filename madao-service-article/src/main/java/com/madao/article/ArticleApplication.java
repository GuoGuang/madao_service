package com.madao.article;

import com.madao.annotation.EnableSpringCloudComponent;
import com.madao.article.search.ApiArticleSearchDao;
import com.madao.config.BasicApplication;
import com.madao.model.dto.article.ArticleSearchDto;
import com.madao.utils.DateUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

/**
 * SpringBoot主配置类只会扫描自己所在的包及其子包下面,需要通过@ComponentScan处理
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@EnableSpringCloudComponent
@EnableJpaRepositories("com.madao.article.dao")
@EnableElasticsearchRepositories("com.madao.article.search")
public class ArticleApplication extends BasicApplication {

	@Autowired
	private ElasticsearchOperations elasticsearchOperations;
	@Autowired
	private ApiArticleSearchDao articleSearchDao;

	public static void main(String[] args) {
		SpringApplication.run(ArticleApplication.class, args);
	}

	/**
	 * 服务启动完成后初始化示例数据
	 */
	@PostConstruct
	public void insertDataSample() {
		var documents = Arrays.asList(
				ArticleSearchDto.builder().startDate(DateUtil.dateStringToDate("2014-11-06")).title("Spring demo on init insert").build(),
				ArticleSearchDto.builder().startDate(DateUtil.dateStringToDate("2014-12-07")).title("Scala eXchange 2014 - London").build(),
				ArticleSearchDto.builder().startDate(DateUtil.dateStringToDate("2014-11-20")).title("Elasticsearch 2014 - Berlin").build(),
				ArticleSearchDto.builder().startDate(DateUtil.dateStringToDate("2014-11-12")).title("AWS London 2014").build(),
				ArticleSearchDto.builder().startDate(DateUtil.dateStringToDate("2014-10-04")).title("JDD14 - Cracow").build()
		);
		articleSearchDao.saveAll(documents);
	}
}
