package com.codeif.article.service.backstage;

import com.codeif.article.dao.backstage.TagsDao;
import com.codeif.db.redis.service.RedisService;
import com.codeif.exception.custom.ResourceNotFoundException;
import com.codeif.pojo.QueryVO;
import com.codeif.pojo.article.QTags;
import com.codeif.pojo.article.Tags;
import com.codeif.utils.QuerydslUtil;
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
		return jpaQueryFactory
				.selectFrom(qTags)
				.where(predicate)
				.offset(queryVO.getPageNum())
				.limit(queryVO.getPageSize())
				.orderBy(sortedColumn)
				.fetchResults();
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
