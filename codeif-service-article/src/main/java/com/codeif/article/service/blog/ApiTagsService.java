package com.codeif.article.service.blog;

import com.codeif.article.dao.backstage.TagsDao;
import com.codeif.constant.CommonConst;
import com.codeif.constant.RedisConstant;
import com.codeif.db.redis.service.RedisService;
import com.codeif.pojo.article.QTags;
import com.codeif.pojo.article.Tags;
import com.codeif.utils.JsonUtil;
import com.codeif.utils.LogBack;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 前台标签
 **/
@Service
public class ApiTagsService {

	private final TagsDao tagsDao;
	private final RedisService redisService;

	@Autowired
	JPAQueryFactory jpaQueryFactory;

	@Autowired
	public ApiTagsService(TagsDao tagsDao,  RedisService redisService) {
		this.tagsDao = tagsDao;
		this.redisService = redisService;
	}

	/**
	 * 查询标签全部列表
	 * @return IPage<Tags>
	 */
	public List<Tags> findTagsByCondition() {
		QTags qTags = QTags.tags;
		List<Tags> tagsQueryResults = jpaQueryFactory
				.selectFrom(qTags)
				.fetch();
		tagsQueryResults.forEach(
				tag->tag.setTagsCount(Long.valueOf(tag.getArticles().size()))
		);
		return tagsQueryResults;
	}

	/**
	 * 根据ID查询标签
	 * @param id 标签id
	 * @return Tags
	 */
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

//		tags = bgTagsDao.selectById(id);
		try {
			redisService.set(RedisConstant.REDIS_KEY_ARTICLE + id, tags, CommonConst.TIME_OUT_DAY);
		} catch (Exception e) {
			LogBack.error("findTagsById->查询标签异常，参数为：{}",id,e);
		}
		return tags;

	}

	/**
	 * 增加
	 * @param tags 实体
	 */
	public void insertTags(Tags tags) {
//		bgTagsDao.insert(tags);
	}

	/**
	 * 修改
	 * @param tags 实体
	 */
	public void updateTagsById(Tags tags) {
		redisService.del( "tags_" + tags.getId());
//		bgTagsDao.updateById(tags);
	}

	/**
	 * 删除
	 * @param tagsIds:文章id集合
	 */
	public void deleteByIds(List tagsIds) {
		redisService.del( "tags_" + tagsIds);
//		bgTagsDao.deleteBatchIds(tagsIds);
	}


}
