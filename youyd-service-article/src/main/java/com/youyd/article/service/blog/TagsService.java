package com.youyd.article.service.blog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youyd.article.dao.blog.TagsDao;
import com.youyd.cache.constant.RedisConstant;
import com.youyd.cache.redis.RedisService;
import com.youyd.constant.CommonConst;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.article.Tags;
import com.youyd.utils.JsonUtil;
import com.youyd.utils.LogBack;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 前台标签
 * @author: LGG
 * @create: 2018-10-13 16:39
 **/
@Service
public class TagsService {

	private final TagsDao bgTagsDao;

	private final RedisService redisService;

	@Autowired
	public TagsService(TagsDao bgTagsDao, RedisService redisService) {
		this.bgTagsDao = bgTagsDao;
		this.redisService = redisService;
	}

	/**
	 * 查询标签全部列表
	 * @return IPage<Tags>
	 */
	public IPage<Tags> findTagsByCondition(Tags tags, QueryVO queryVO){
		Page<Tags> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());
		LambdaQueryWrapper<Tags> queryWrapper = new LambdaQueryWrapper<>();
		if (StringUtils.isNotEmpty(tags.getName())) {
			queryWrapper.like(Tags::getName, tags.getName());
		}
		if (StringUtils.isNotEmpty(tags.getState())) {
			queryWrapper.eq(Tags::getState, tags.getState());
		}
		return bgTagsDao.selectPage(pr, queryWrapper);
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
			LogBack.error("findTagsById->查询标签异常，参数为：{}",id,e);
		}

		tags = bgTagsDao.selectById(id);
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
		bgTagsDao.insert(tags);
	}

	/**
	 * 修改
	 * @param tags 实体
	 */
	public void updateTagsById(Tags tags) {
		redisService.del( "tags_" + tags.getId());
		bgTagsDao.updateById(tags);
	}

	/**
	 * 删除
	 * @param tagsIds:文章id集合
	 */
	public void deleteByIds(List tagsIds) {
		redisService.del( "tags_" + tagsIds );
		bgTagsDao.deleteBatchIds(tagsIds);
	}


}
