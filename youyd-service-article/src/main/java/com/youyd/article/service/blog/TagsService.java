package com.youyd.article.service.blog;

import com.youyd.article.dao.blog.TagsDao;
import com.youyd.cache.constant.RedisConstant;
import com.youyd.cache.redis.RedisService;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.article.Tags;
import com.youyd.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 前台标签
 * @author: LGG
 * @create: 2018-10-13 16:39
 **/
@Service
public class TagsService {

	@Autowired
	private TagsDao bgTagsDao;

	@Autowired
	private RedisService redisService;

	/**
	 * 查询全部列表
	 * @return
	 */
	public ArrayList<Tags> findTagsByCondition(Tags tags, QueryVO queryVO){
		ArrayList<Tags> tagsPage = bgTagsDao.findTagsByCondition(queryVO);
		return tagsPage;
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Tags findTagsByPrimaryKey(String id) {
		Tags tags = null;
		try {
			Object mapJson = redisService.get(RedisConstant.REDIS_KEY_ARTICLE + id);
			if (mapJson != null) {
				return JsonUtil.mapToPojo(mapJson, Tags.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		tags = bgTagsDao.selectById(id);
		System.out.println("555");
		try {
			redisService.set(RedisConstant.REDIS_KEY_ARTICLE + id, tags, RedisConstant.REDIS_TIME_DAY);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tags;

	}

	/**
	 * 增加
	 * @param tags
	 */
	public void insertTags(Tags tags) {
		bgTagsDao.insert(tags);
	}

	/**
	 * 修改
	 * @param tags
	 */
	public void updateByPrimaryKeySelective(Tags tags) {
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
