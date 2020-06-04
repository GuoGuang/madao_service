package com.codeway.article.service.blog;

import com.codeway.article.dao.blog.ApiArticleDao;
import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.pojo.article.Article;
import com.codeway.utils.JsonUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiArticleService {

	private final ApiArticleDao articleDao;

	@Autowired
	JPAQueryFactory jpaQueryFactory;

	@Autowired
	public ApiArticleService(ApiArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public Page<Article> findArticleByCondition(Article article, String keyword, Pageable pageable) {
		// 默认首页
		Specification<Article> condition = (root, query, builder) -> {
			List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(article.getCategoryId())) {
				predicates.add(builder.equal(root.get("categoryId"), article.getTitle()));
			}
			if (StringUtils.isNotEmpty(keyword)) {
				predicates.add(builder.like(root.get("title"), "%" + keyword + "%"));
			}
			return query.where(predicates.toArray(new javax.persistence.criteria.Predicate[0])).getRestriction();
		};
		return articleDao.findAll(condition, pageable);
	}

	public Page<Article> findArticleByTagId(String tagId, Pageable pageable) {
		return articleDao.findArticleByTagId(tagId, pageable);
	}

	public Article findArticleById(String articleId) {
		Article article = articleDao.findById(articleId).orElseThrow(ResourceNotFoundException::new);
		article.setRelated(JsonUtil.toJsonString(articleDao.findRelatedByRand()));
		article.setCategoryId(article.getCategory().getName());
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
	 */
	public void updateUpVote(String id) {
		articleDao.updateUpVote(id);
	}

	/**
	 * 取消点赞
	 */
	public void updateUnUpVote(String id) {
		articleDao.updateUnUpVote(id);
	}

}
