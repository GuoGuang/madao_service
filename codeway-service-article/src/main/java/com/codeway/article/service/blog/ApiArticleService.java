package com.codeway.article.service.blog;

import com.codeway.article.dao.backstage.CommentDao;
import com.codeway.article.dao.blog.ApiArticleDao;
import com.codeway.article.service.backstage.CategoryService;
import com.codeway.db.redis.service.RedisService;
import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.model.pojo.article.Article;
import com.codeway.model.pojo.article.Comment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ApiArticleService {

	private final ApiArticleDao articleDao;
	private final CategoryService categoryService;
	private final CommentDao commentDao;

	private final RedisService redisService;

	public ApiArticleService(ApiArticleDao articleDao,
	                         CategoryService categoryService,
	                         CommentDao commentDao,
	                         RedisService redisService) {
		this.articleDao = articleDao;
		this.commentDao = commentDao;
		this.categoryService = categoryService;
		this.redisService = redisService;
	}


	public Page<Article> findArticleByCondition(Article article, String keyword, Pageable pageable) {
		// 默认首页
		Specification<Article> condition = (root, query, builder) -> {
			List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(article.getCategoryId())) {
				predicates.add(builder.equal(root.get("category").get("id"), article.getCategoryId()));
			}
			if (StringUtils.isNotEmpty(keyword)) {
				predicates.add(builder.like(root.get("title"), "%" + keyword + "%"));
			}
			return query.where(predicates.toArray(new javax.persistence.criteria.Predicate[0])).getRestriction();
		};
		Page<Article> pageContent = articleDao.findAll(condition, pageable);
		List<String> articleIds = pageContent.getContent().stream().map(Article::getId).collect(Collectors.toList());
		Map<String, List<Comment>> idKeysAndComments = commentDao.findByArticleIdIn(articleIds).stream().collect(Collectors.groupingBy(Comment::getArticleId));
		pageContent.forEach(articleInfo -> {
			if (idKeysAndComments.get(articleInfo.getId()) != null) {
				articleInfo.setComment(idKeysAndComments.get(articleInfo.getId()).size());
			}
		});
		return pageContent;
	}

	public Page<Article> findArticleByTagId(String tagId, Pageable pageable) {
		return articleDao.findArticleByTagId(tagId, pageable);
	}

	public Article findArticleById(String articleId) {
		Article article = articleDao.findById(articleId).orElseThrow(ResourceNotFoundException::new);
		article.setRelated(articleDao.findRelatedByRand());
		article.setCategoryId(article.getCategory().getName());
		return article;
	}

	/**
	 * 点赞
	 */
	public void upVote(String id) {
		articleDao.updateUpVote(id);
	}

	/**
	 * 取消点赞
	 */
	public void unUpVote(String id) {
		articleDao.updateUnUpVote(id);
	}

}
