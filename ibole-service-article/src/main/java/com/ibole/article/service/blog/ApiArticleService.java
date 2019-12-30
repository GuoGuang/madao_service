package com.ibole.article.service.blog;

import com.ibole.article.dao.blog.ApiArticleDao;
import com.ibole.article.service.backstage.CategoryService;
import com.ibole.db.redis.service.RedisService;
import com.ibole.exception.custom.ResourceNotFoundException;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.article.Article;
import com.ibole.pojo.article.QArticle;
import com.ibole.utils.QuerydslUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章板块:文章服务
 **/
@Service
public class ApiArticleService {

	private final ApiArticleDao articleDao;
	private final CategoryService categoryService;

	private final RedisService redisService;

	@Autowired
	JPAQueryFactory jpaQueryFactory;

	@Autowired
	public ApiArticleService(ApiArticleDao articleDao, CategoryService categoryService, RedisService redisService) {
		this.articleDao = articleDao;
		this.categoryService = categoryService;
		this.redisService = redisService;
	}


	public QueryResults<Article> findArticleByCondition(QueryVO queryVO) {
		QArticle qArticle = QArticle.article;
		QueryResults<Article> queryResults = jpaQueryFactory
				.selectFrom(qArticle)
				.offset(queryVO.getPageNum())
				.limit(queryVO.getPageSize())
				.orderBy(QuerydslUtil.getSortedColumn(Order.DESC, qArticle))
				.fetchResults();
		queryResults.getResults().forEach(articleInfo -> articleInfo.setCategory(categoryService.findCategoryById(articleInfo.getCategoryId())));
		return queryResults;
	}


	public Article findArticleById(String articleId) {
		return articleDao.findById(articleId).orElseThrow(ResourceNotFoundException::new);
	}

	/**
	 * 增加
	 * @param article 实体
	 */
	public void insertArticle(Article article) {
//		articleDao.insert(article);
	}

	/**
	 * 修改
	 * @param article 实体
	 */
	public void updateByPrimaryKeySelective(Article article) {
//		redisService.del( "article_" + article.getId());
//		articleDao.updateById(article);
	}

	/**
	 * 删除
	 * @param articleIds:文章id集合
	 */
	public void deleteArticleByIds(List<String> articleIds) {
//		articleDao.deleteBatchIds(articleIds);
	}


	/**
	 * 点赞
	 * @param id 文章ID
	 * @return
	 */
	public int updateThumbUp(String id) {
//		return articleDao.updateThumbUp(id);
		return 1;
	}

}
