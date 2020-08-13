package com.codeway.article.service.blog;

import com.codeway.article.dao.backstage.TagsDao;
import com.codeway.constant.CommonConst;
import com.codeway.constant.RedisConstant;
import com.codeway.db.redis.service.RedisService;
import com.codeway.model.pojo.article.Tag;
import com.codeway.utils.LogBack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiTagsService {

	private final TagsDao tagsDao;
	private final RedisService redisService;

	public ApiTagsService(TagsDao tagsDao, RedisService redisService) {
		this.tagsDao = tagsDao;
		this.redisService = redisService;
	}

	public List<Tag> findTagsByCondition(Tag tags, Pageable pageable) {
		Page<Tag> tagsQueryResults = tagsDao.findAll(pageable);
		tagsQueryResults.forEach(
				tag -> tag.setTagsCount(Long.valueOf(tag.getArticles().size()))
		);
		return tagsQueryResults.getContent();
	}

	public Tag findTagsById(String id) {
		Tag tags = null;
//		try {
//			Object mapJson = redisService.get(RedisConstant.REDIS_KEY_ARTICLE + id);
//			if (mapJson != null) {
//				return JsonUtil.jsonToPojo(mapJson.toString(), Tag.class);
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
}
