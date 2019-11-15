package com.ibole.search.dao;

import com.ibole.search.pojo.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @description 集成ElasticSearch的文章搜索系统
 *  集成SpringDataElasticSearch 之后只需要继承ElasticsearchRepository就可以,
 * @author LGG
 * @create 2018-10-14 18:44
 **/
// <Article,String> Article的主键是String类型
@Component
public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {

	/**
	 * 检索
	 * @param title :搜索的标题
	 * @param content: 搜索的内容
	 * @return Article
	 */
	//List<Article> searchArticleByCondition(String title, String content);
}
