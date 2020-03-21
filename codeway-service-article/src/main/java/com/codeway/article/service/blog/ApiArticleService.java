package com.codeway.article.service.blog;

import com.codeway.article.dao.backstage.TagsDao;
import com.codeway.article.dao.blog.ApiArticleDao;
import com.codeway.article.service.backstage.CategoryService;
import com.codeway.db.redis.service.RedisService;
import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.pojo.QueryVO;
import com.codeway.pojo.article.Article;
import com.codeway.pojo.article.QArticle;
import com.codeway.utils.JsonUtil;
import com.codeway.utils.QuerydslUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
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
	private final TagsDao tagsDao;

	private final RedisService redisService;

	@Autowired
	JPAQueryFactory jpaQueryFactory;

	@Autowired
	public ApiArticleService(ApiArticleDao articleDao,
	                         CategoryService categoryService,
	                         TagsDao tagsDao,
	                         RedisService redisService) {
		this.articleDao = articleDao;
		this.tagsDao = tagsDao;
		this.categoryService = categoryService;
		this.redisService = redisService;
	}


	public QueryResults<Article> findArticleByCondition(Article article, String categoryId, QueryVO queryVO) {
		QArticle qArticle = QArticle.article;
		Predicate predicate = null;
		// 默认首页
		if (StringUtils.isNotEmpty(article.getCategoryId())) {
			predicate = ExpressionUtils.and(predicate, qArticle.category.id.eq(categoryId));
		}
		if (StringUtils.isNotEmpty(queryVO.getKeyword())) {
			predicate = ExpressionUtils.and(predicate, qArticle.title.like(queryVO.getKeyword())
					.or(qArticle.content.like(queryVO.getKeyword())));
		}

		QueryResults<Article> queryResults = jpaQueryFactory
				.selectFrom(qArticle)
				.where(predicate)
				.offset(queryVO.getPageNum())
				.limit(queryVO.getPageSize())
				.orderBy(QuerydslUtil.getSortedColumn(Order.DESC, qArticle))
				.fetchResults();
		return queryResults;
	}

	public Article findArticleById(String articleId) {
		Article article = articleDao.findById(articleId).orElseThrow(ResourceNotFoundException::new);
		article.setRelated(JsonUtil.toJsonString(articleDao.findRelatedByRand()));
		return article;
	}


	/**
	 * 增加
	 *
	 * @param article 实体
	 */
	public void insertArticle(Article article) {
//		articleDao.insert(article);
	}

	/**
	 * 修改
	 *
	 * @param article 实体
	 */
	public void updateByPrimaryKeySelective(Article article) {
//		redisService.del( "article_" + article.getId());
//		articleDao.updateById(article);
	}

	/**
	 * 删除
	 *
	 * @param articleIds:文章id集合
	 */
	public void deleteArticleByIds(List<String> articleIds) {
//		articleDao.deleteBatchIds(articleIds);
	}


	/**
	 * 点赞
	 *
	 * @param id 文章ID
	 * @return
	 */
	public int updateThumbUp(String id) {
//		return articleDao.updateThumbUp(id);
		return 1;
	}

}
