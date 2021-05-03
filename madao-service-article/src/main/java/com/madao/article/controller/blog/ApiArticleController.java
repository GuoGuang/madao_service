package com.madao.article.controller.blog;

import com.madao.article.service.blog.ApiArticleService;
import com.madao.constant.ArticleConst;
import com.madao.constant.CommonConst;
import com.madao.constant.RedisConstant;
import com.madao.db.redis.service.RedisService;
import com.madao.model.dto.article.ArticleDto;
import com.madao.utils.JsonData;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@RestController
@RequestMapping(value = "/api/ar/article", produces = "application/json")
public class ApiArticleController {

    private final ApiArticleService articleService;
    private final RedisService redisService;

    public ApiArticleController(ApiArticleService articleService, RedisService redisService) {
        this.articleService = articleService;
        this.redisService = redisService;
    }

    @ApiOperation(value = "查询集合", notes = "Article")
    @GetMapping
    public JsonData<Object> findArticleByCondition(ArticleDto articleDto, String keyword, String sortType,
                                                   @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
        // 最热文章
        if (ArticleConst.SORT_TYPE_HOT.equals(sortType)) {
            List<Object> hotList = redisService.lGet("ARTICLE_HOT", 0, 10);
            return JsonData.success(hotList);
        }
        Page<ArticleDto> result = articleService.findArticleByCondition(articleDto, keyword, pageable);
        return JsonData.success(result);
    }

    @ApiOperation(value = "最热列表", notes = "最热列表")
    @GetMapping("/hot")
    public JsonData<Object> findArticleHot(String sortType) {
        List<Object> hotList = redisService.lGet("ARTICLE_HOT", 0, 10);
        return JsonData.success(hotList);
    }

    @ApiOperation(value = "根据标签id查询文章", notes = "根据标签id查询文章")
    @GetMapping("/tag/{tagId}")
    public JsonData<Page<ArticleDto>> findArticleByTagId(@PathVariable String tagId,
                                                         @PageableDefault(sort = "create_at", direction = DESC) Pageable pageable) {
        Page<ArticleDto> result = articleService.findArticleByTagId(tagId, pageable);
        return JsonData.success(result);
    }

    @GetMapping(value = "/{articleId}")
    public JsonData<Object> findArticleByPrimaryKey(@PathVariable String articleId) {
        Object mapJson = redisService.get(RedisConstant.REDIS_KEY_ARTICLE + articleId).orElse(null);
        if (mapJson == null) {
            ArticleDto articleResult = articleService.findArticleById(articleId);
            redisService.set(RedisConstant.REDIS_KEY_ARTICLE + articleId, articleResult, CommonConst.TIME_OUT_DAY);
            return JsonData.success(articleResult);
        }
        return JsonData.success(mapJson);
    }

    @ApiOperation(value = "点赞", notes = "id")
    @PutMapping(value = "/like/{articleId}")
    public JsonData<Void> upVote(@PathVariable String articleId) {
        articleService.upVote(articleId);
        redisService.del(RedisConstant.REDIS_KEY_ARTICLE + articleId);
        return JsonData.success();
    }

    @ApiOperation(value = "取消点赞", notes = "id")
    @DeleteMapping(value = "/like/{articleId}")
    public JsonData<Void> unUpVote(@PathVariable String articleId) {
        articleService.unUpVote(articleId);
        redisService.del(RedisConstant.REDIS_KEY_ARTICLE + articleId);
        return JsonData.success();
    }

    @GetMapping("/admin")
    @ApiOperation(value = "获取作者文章数据", notes = "Admin")
    public JsonData<Map<String, Object>> findAdminInfo() {
        return JsonData.success(articleService.findAuthorDetail());
    }
}
