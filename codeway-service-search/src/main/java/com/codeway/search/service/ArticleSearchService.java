package com.codeway.search.service;

import com.codeway.search.dao.ArticleSearchDao;
import com.codeway.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章板块: 文章类,集成ES
 **/
@Service
public class ArticleSearchService {

	@Autowired
	private ArticleSearchDao articleSearchDao;

	/**
	 * 按照文章标题文章内容搜索
	 * @return
	 * @param keywords
	 * @param page
	 * @param size
	 */
	public List<Article> searchArticleByCondition(String keywords, Integer page, Integer size) {
		//return articleSearchDao.searchArticleByCondition(null,null);
		return null;
	}
/*
	*//**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 *//*
	public Article findArticleByPrimaryKey(String id) {
		Article article = articleSearchDao.findArticleByPrimaryKey(id);
		return article;
	}

	*//**
	 * 增加
	 * @param article
	 *//*
	public void insertArticle(Article article) {
		articleSearchDao.save(article);
	}

	*//**
	 * 修改
	 * @param article
	 *//*
	public void updateByPrimaryKeySelective(Article article) {
		articleSearchDao.updateByPrimaryKeySelective(article);
	}

	*//**
	 * 删除
	 * @param id
	 *//*
	public void deleteById(String id) {
		articleSearchDao.deleteByPrimaryKey(null);
	}

	*//**
	 * 审核文章
	 * @param id
	 *//*
	public void examine(String id){
		articleSearchDao.examine(id);
	}

	*//**
	 * 点赞
	 * @param id 文章ID
	 * @return
	 *//*
	public int updateThumbup(String id){
		return articleSearchDao.updateThumbup(id);
	}*/
}
