package com.madao.search.service;

import com.madao.search.dao.ArticleSearchDao;
import com.madao.search.pojo.Article;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArticleSearchService {

	private final ArticleSearchDao articleSearchDao;

	public ArticleSearchService(ArticleSearchDao articleSearchDao) {
		this.articleSearchDao = articleSearchDao;
	}

	public List<Article> searchArticleByCondition(String keywords, Integer page, Integer size) {
		QueryBuilder queryBuilder = QueryBuilders.termQuery("user", "kimchy");
		Iterable<Article> all = articleSearchDao.findAll();
		return null;
	}

}
