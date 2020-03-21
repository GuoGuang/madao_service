package com.codeway.article.service.backstage;

import com.codeway.article.dao.backstage.ArticleDao;
import com.codeway.article.dao.backstage.TagsDao;
import com.codeway.db.redis.service.RedisService;
import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.pojo.QueryVO;
import com.codeway.pojo.article.QTags;
import com.codeway.pojo.article.Tags;
import com.codeway.utils.QuerydslUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsService {

	private final TagsDao tagsDao;
	@Autowired
	private ArticleDao articleDao;

	private final RedisService redisService;

	@Autowired
	JPAQueryFactory jpaQueryFactory;

	@Autowired
	public TagsService(TagsDao tagsDao, RedisService redisService) {
		this.tagsDao = tagsDao;
		this.redisService = redisService;
	}

	public QueryResults<Tags> findTagsByCondition(Tags tags, QueryVO queryVO) {

		QTags qTags = QTags.tags;
		Predicate predicate = null;
		OrderSpecifier<?> sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qTags);
		if (StringUtils.isNotEmpty(tags.getName())) {
			predicate = ExpressionUtils.and(predicate, qTags.name.like(tags.getName()));
		}
		if (tags.getState() != null) {
			predicate = ExpressionUtils.and(predicate, qTags.state.eq(tags.getState()));
		}
		if (StringUtils.isNotEmpty(queryVO.getFieldSort())) {
			sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qTags, queryVO.getFieldSort());
		}
		QueryResults<Tags> tagsQueryResults = jpaQueryFactory
				.selectFrom(qTags)
				.where(predicate)
				.offset(queryVO.getPageNum())
				.limit(queryVO.getPageSize())
				.orderBy(sortedColumn)
				.fetchResults();
		tagsQueryResults.getResults().forEach(
				tag->tag.setTagsCount(Long.valueOf(tag.getArticles().size()))
		);
		return tagsQueryResults;
	}

	public Tags findTagsById(String id) {
		return tagsDao.findById(id).orElseThrow(ResourceNotFoundException::new);
	}

	public void saveOrUpdate(Tags tags) {
		tagsDao.save(tags);
	}

	public void deleteBatch(List<String> tagsIds) {
		tagsDao.deleteBatch(tagsIds);
	}


}
