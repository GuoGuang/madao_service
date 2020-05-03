package com.codeway.article.controller.blog;

import com.codeway.article.service.blog.ApiArticleService;
import com.codeway.constant.ArticleConst;
import com.codeway.constant.CommonConst;
import com.codeway.constant.RedisConstant;
import com.codeway.db.redis.service.RedisService;
import com.codeway.pojo.QueryVO;
import com.codeway.pojo.article.Article;
import com.codeway.utils.JsonData;
import com.codeway.utils.JsonUtil;
import com.querydsl.core.QueryResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Api(tags = "前台网站文章")
@RestController
@RequestMapping(value = "/api/ar/article", produces = "application/json")
public class ApiArticleController {

    @Autowired
    private ApiArticleService articleService;
    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "查询集合", notes = "Article")
    @GetMapping
    public JsonData<Object> findArticleByCondition(Article article, String keyword,String sortType,
                                                   @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
        // 最热文章
    	if (ArticleConst.SORT_TYPE_HOT.equals(sortType)) {
            List<Object> hotList = redisService.lGet("ARTICLE_HOT", 0, 10);
            return JsonData.success(hotList);
        }
	    Page<Article> result = articleService.findArticleByCondition(article,keyword,pageable);
        return JsonData.success(result);
    }

    @ApiOperation(value = "根据标签id查询文章", notes = "根据标签id查询文章")
    @GetMapping("/tag/{tagId}")
    public JsonData<Page<Article>> findArticleByTagId(@PathVariable String tagId,
                                               @PageableDefault(sort = "create_at", direction = DESC) Pageable pageable) {
	    Page<Article> result = articleService.findArticleByTagId(tagId,pageable);
        return JsonData.success(result);
    }

    @GetMapping(value = "/{articleId}")
    public JsonData<Article> findArticleByPrimaryKey(@PathVariable String articleId) {
        Object mapJson = redisService.get(RedisConstant.REDIS_KEY_ARTICLE + articleId);
        if (mapJson == null) {
	        Article articleResult = articleService.findArticleById(articleId);
	        redisService.set(RedisConstant.REDIS_KEY_ARTICLE + articleId, JsonUtil.toJsonString(articleResult), CommonConst.TIME_OUT_DAY);
            return JsonData.success(articleResult);
        }
	    Article article = JsonUtil.jsonToPojo(mapJson, Article.class);
        return JsonData.success(article);
    }

	@ApiOperation(value = "点赞", notes = "id")
	@PutMapping(value = "/like/{articleId}")
	public JsonData<Void> upVote(@PathVariable String articleId) {
		articleService.updateUpVote(articleId);
		redisService.del(RedisConstant.REDIS_KEY_ARTICLE + articleId);
		return JsonData.success();
	}

	@ApiOperation(value = "取消点赞", notes = "id")
	@DeleteMapping(value = "/like/{articleId}")
	public JsonData<Void> unUpVote(@PathVariable String articleId) {
		articleService.updateUnUpVote(articleId);
		redisService.del(RedisConstant.REDIS_KEY_ARTICLE + articleId);
		return JsonData.success();
	}

}
