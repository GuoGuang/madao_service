package com.codeway.search.service;

import com.codeway.search.dao.ArticleSearchDao;
import com.codeway.search.pojo.Article;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;


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
