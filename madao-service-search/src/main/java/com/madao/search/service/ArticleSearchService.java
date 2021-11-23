package com.madao.search.service;

import com.madao.search.dao.ArticleSearchDao;
import com.madao.search.pojo.Article;
import lombok.AllArgsConstructor;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Service
@AllArgsConstructor
public class ArticleSearchService{

    private final ArticleSearchDao articleSearchDao;

    public List<Article> searchArticleByCondition(String keywords, Integer page, Integer size) {
        QueryBuilder queryBuilder = QueryBuilders.termQuery("user", "kimchy");
        Iterable<Article> all = articleSearchDao.findAll();
        return null;
    }

	public Article findArticleByPrimaryKey(String id) {
		return articleSearchDao.findById(id).orElse(null);
	}

	public void insertArticle(Article article) {
		articleSearchDao.save(article);
	}

	public void updateByPrimaryKey(Article article) {
		articleSearchDao.save(article);
	}

	public void deleteById(String id) {
		articleSearchDao.deleteById(id);
	}
}
