package com.madao.article.service.blog;

import com.madao.article.dao.backstage.ArticleTagDao;
import com.madao.article.dao.backstage.TagDao;
import com.madao.article.mapper.TagMapper;
import com.madao.constant.CommonConst;
import com.madao.constant.RedisConstant;
import com.madao.db.redis.service.RedisService;
import com.madao.model.dto.article.TagDto;
import com.madao.model.pojo.article.ArticleTag;
import com.madao.utils.LogBack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ApiTagsService {

    private final TagDao tagDao;
    private final ArticleTagDao articleTagDao;
    private final RedisService redisService;
    private final TagMapper tagMapper;

    public ApiTagsService(TagDao tagDao, ArticleTagDao articleTagDao, RedisService redisService, TagMapper tagMapper) {
        this.tagDao = tagDao;
        this.articleTagDao = articleTagDao;
        this.redisService = redisService;
        this.tagMapper = tagMapper;
    }

    public List<TagDto> findTagsByCondition(TagDto tagDto, Pageable pageable) {
        LogBack.info("查询参数---------->", tagDto);
        Page<TagDto> tagsQueryResults = tagDao.findAll(pageable)
                .map(tagMapper::toDto);

        List<ArticleTag> articleTags = articleTagDao.findAllByTagIdIn(tagsQueryResults.getContent().stream().map(TagDto::getId).collect(Collectors.toList()));

        Map<String, List<ArticleTag>> tagIds = articleTags.stream()
                .collect(Collectors.groupingBy(ArticleTag::getTagId));

        tagsQueryResults.forEach(tag -> {
            if (tagIds.get(tag.getId()) != null) {
                tag.setTagsCount(tagIds.get(tag.getId()).size());
            }
        });

        return tagsQueryResults.getContent();
    }

    public TagDto findTagsById(String id) {
        TagDto tagDto = null;
//		try {
//			Object mapJson = redisService.get(RedisConstant.REDIS_KEY_ARTICLE + id);
//			if (mapJson != null) {
//				return JsonUtil.jsonToPojo(mapJson.toString(), Tag.class);
//			}
//		} catch (Exception e) {
//			LogBack.error("findTagsById->查询标签异常，参数为：{}", id, e);
//        }

        tagDto = tagDao.findById(id).map(tagMapper::toDto).orElse(null);
        try {
            redisService.set(RedisConstant.REDIS_KEY_ARTICLE + id, tagDto, CommonConst.TIME_OUT_DAY);
        } catch (Exception e) {
            LogBack.error("findTagsById->查询标签异常，参数为：{}", id, e);
        }
        return tagDto;

    }
}
