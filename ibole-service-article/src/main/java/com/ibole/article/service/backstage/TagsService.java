package com.ibole.article.service.backstage;

import com.ibole.article.dao.backstage.TagsDao;
import com.ibole.constant.CommonConst;
import com.ibole.constant.RedisConstant;
import com.ibole.db.redis.service.RedisService;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.article.QTags;
import com.ibole.pojo.article.Tags;
import com.ibole.utils.DateUtil;
import com.ibole.utils.JsonUtil;
import com.ibole.utils.LogBack;
import com.ibole.utils.QuerydslUtil;
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
		Tags tags = null;
		try {
			Object mapJson = redisService.get(RedisConstant.REDIS_KEY_ARTICLE + id);
			if (mapJson != null) {
				return JsonUtil.jsonToPojo(mapJson.toString(), Tags.class);
			}
		} catch (Exception e) {
			LogBack.error("findTagsById->查询标签异常，参数为：{}", id, e);
		}
		try {
			redisService.set(RedisConstant.REDIS_KEY_ARTICLE + id, tags, CommonConst.TIME_OUT_DAY);
		} catch (Exception e) {
			LogBack.error("findTagsById->查询标签异常，参数为：{}", id, e);
		}
		return tags;

	}

	public void saveOrUpdate(Tags tags) {
		tags.setUpdateAt(DateUtil.getTimestamp());
		if (tags.getId() == null) {
			tags.setCreateAt(DateUtil.getTimestamp());
		}
		tagsDao.save(tags);
	}

	public void deleteBatch(List<String> tagsIds) {
		tagsDao.deleteBatch(tagsIds);
	}


}
