package com.madao.article.service.blog;

import com.madao.article.dao.backstage.CommentDao;
import com.madao.article.dao.blog.ApiArticleDao;
import com.madao.article.mapper.ArticleMapper;
import com.madao.exception.custom.ResourceNotFoundException;
import com.madao.model.dto.article.ArticleDto;
import com.madao.model.entity.article.Article;
import com.madao.model.entity.article.Comment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Service
@CacheConfig(cacheNames = "article")
public class ApiArticleService {

	private final ApiArticleDao articleDao;
	private final ArticleMapper articleMapper;
	private final CommentDao commentDao;

	public ApiArticleService(ApiArticleDao articleDao,
	                         ArticleMapper articleMapper,
	                         CommentDao commentDao) {
		this.articleDao = articleDao;
		this.articleMapper = articleMapper;
		this.commentDao = commentDao;
	}


	public Page<ArticleDto> findArticleByCondition(ArticleDto articleDto, String keyword, Pageable pageable) {
		// 默认首页
		Specification<Article> condition = (root, query, builder) -> {
			List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(articleDto.getCategoryId())) {
				predicates.add(builder.equal(root.get("categoryId"), articleDto.getCategoryId()));
			}
			if (StringUtils.isNotEmpty(keyword)) {
				predicates.add(builder.like(root.get("title"), "%" + keyword + "%"));
			}
			return query.where(predicates.toArray(new javax.persistence.criteria.Predicate[0])).getRestriction();
		};
		Page<ArticleDto> pageContent = articleDao.findAll(condition, pageable).map(articleMapper::toDto);
		List<String> articleIds = pageContent.getContent().stream().map(ArticleDto::getId).collect(Collectors.toList());
		Map<String, List<Comment>> idKeysAndComments = commentDao.findByArticleIdIn(articleIds).stream().collect(Collectors.groupingBy(Comment::getArticleId));
		pageContent.forEach(articleInfo -> {
			if (idKeysAndComments.get(articleInfo.getId()) != null) {
				articleInfo.setComment(idKeysAndComments.get(articleInfo.getId()).size());
			}
		});
		return pageContent;
	}

	public Page<ArticleDto> findArticleByTagId(String tagId, Pageable pageable) {
		return articleDao.findArticleByTagId(tagId, pageable).map(articleMapper::toDto);
	}

	@Cacheable(key = "#articleId", unless = "#result==null ")
	public ArticleDto findArticleById(String articleId) {
		ArticleDto article = articleDao.findById(articleId).map(articleMapper::toDto).orElseThrow(ResourceNotFoundException::new);
		article.setRelated(articleMapper.toDto(articleDao.findRelatedByRand()));
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

	public Map<String, Object> findAuthorDetail() {
		return articleDao.findAuthorDetailByMap();
	}
}
