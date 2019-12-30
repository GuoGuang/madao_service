package com.ibole.article.service.backstage;

import com.ibole.article.dao.backstage.TagsDao;
import com.ibole.constant.CommonConst;
import com.ibole.constant.RedisConstant;
import com.ibole.db.redis.service.RedisService;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.article.Tags;
import com.ibole.utils.JsonUtil;
import com.ibole.utils.LogBack;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后台标签
 **/
@Service
public class TagsService {

	private final TagsDao bgTagsDao;

	private final RedisService redisService;

	@Autowired
	JPAQueryFactory jpaQueryFactory;

	@Autowired
	public TagsService(TagsDao bgTagsDao, RedisService redisService) {
		this.bgTagsDao = bgTagsDao;
		this.redisService = redisService;
	}

	/**
	 * 查询标签全部列表
	 *
	 * @return IPage<Tags>
	 */
	public List<Tags> findTagsByCondition(Tags tags, QueryVO queryVO) {
//		Page<Tags> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());
//		LambdaQueryWrapper<Tags> queryWrapper = new LambdaQueryWrapper<>();
//		if (StringUtils.isNotEmpty(tags.getName())) {
//			queryWrapper.like(Tags::getName, tags.getName());
//		}
//		if (StringUtils.isNotEmpty(tags.getState())) {
//			queryWrapper.eq(Tags::getState, tags.getState());
//		}
//		return bgTagsDao.selectPage(pr, queryWrapper);
		return null;
	}

	/**
	 * 根据ID查询标签
	 *
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
			LogBack.error("findTagsById->查询标签异常，参数为：{}", id, e);
		}
		return tags;

	}

	/**
	 * 增加
	 *
	 * @param tags 实体
	 */
	public void insertTags(Tags tags) {
//		bgTagsDao.insert(tags);
	}

	/**
	 * 修改
	 *
	 * @param tags 实体
	 */
	public void updateTagsById(Tags tags) {
		redisService.del("tags_" + tags.getId());
//		bgTagsDao.updateById(tags);
	}

	/**
	 * 删除
	 *
	 * @param tagsIds:文章id集合
	 */
	public void deleteByIds(List tagsIds) {
		redisService.del("tags_" + tagsIds);
//		bgTagsDao.deleteBatchIds(tagsIds);
	}


}
