package com.codeway.article.service.blog;

import com.codeway.article.dao.backstage.TagsDao;
import com.codeway.constant.CommonConst;
import com.codeway.constant.RedisConstant;
import com.codeway.db.redis.service.RedisService;
import com.codeway.pojo.article.Tags;
import com.codeway.utils.LogBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiTagsService {

	private final TagsDao tagsDao;
	private final RedisService redisService;

	public ApiTagsService(TagsDao tagsDao,  RedisService redisService) {
		this.tagsDao = tagsDao;
		this.redisService = redisService;
	}

	public List<Tags> findTagsByCondition(Tags tags, Pageable pageable) {
		Page<Tags> tagsQueryResults = tagsDao.findAll(pageable);
		tagsQueryResults.forEach(
				tag->tag.setTagsCount(Long.valueOf(tag.getArticles().size()))
		);
		return tagsQueryResults.getContent();
	}

	public Tags findTagsById(String id) {
		Tags tags = null;
//		try {
//			Object mapJson = redisService.get(RedisConstant.REDIS_KEY_ARTICLE + id);
//			if (mapJson != null) {
//				return JsonUtil.jsonToPojo(mapJson.toString(), Tags.class);
//			}
//		} catch (Exception e) {
//			LogBack.error("findTagsById->查询标签异常，参数为：{}", id, e);
//        }

		tags = tagsDao.findById(id).orElse(null);
		try {
			redisService.set(RedisConstant.REDIS_KEY_ARTICLE + id, tags, CommonConst.TIME_OUT_DAY);
		} catch (Exception e) {
			LogBack.error("findTagsById->查询标签异常，参数为：{}",id,e);
		}
		return tags;

	}

	public void insertTags(Tags tags) {
//		bgTagsDao.insert(tags);
	}

	public void updateTagsById(Tags tags) {
		redisService.del( "tags_" + tags.getId());
//		bgTagsDao.updateById(tags);
	}

	public void deleteByIds(List tagsIds) {
		redisService.del( "tags_" + tagsIds);
//		bgTagsDao.deleteBatchIds(tagsIds);
	}


}
