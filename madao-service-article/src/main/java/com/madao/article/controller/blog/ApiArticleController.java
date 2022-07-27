package com.madao.article.controller.blog;

import com.madao.article.service.blog.ApiArticleService;
import com.madao.constant.CommonConst;
import com.madao.constant.RedisConstant;
import com.madao.model.dto.article.ArticleDto;
import com.madao.model.entity.article.Article;
import com.madao.utils.JsonData;
import com.madao.utils.RedisUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ApiArticleController {

	private final ApiArticleService articleService;
	private final RedisUtil redisUtil;

	@Operation(summary = "查询集合", description = "Article")
	@GetMapping
	public JsonData<Page<ArticleDto>> findArticleByCondition(ArticleDto articleDto, String keyword, String sortType,
	                                                         @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
		Page<ArticleDto> result = articleService.findArticleByCondition(articleDto, keyword, pageable);
		return JsonData.success(result);
	}

	@Operation(summary = "最热列表", description = "最热列表")
	@GetMapping("/hot")
	public JsonData<List<Article>> findArticleHot(String sortType) {
		List<Article> hotList = redisUtil.lGet("ARTICLE_HOT", 0, 10);
		return JsonData.success(hotList);
	}

	@Operation(summary = "根据标签id查询文章", description = "根据标签id查询文章")
	@GetMapping("/tag/{tagId}")
	public JsonData<Page<ArticleDto>> findArticleByTagId(@PathVariable String tagId,
	                                                     @PageableDefault(sort = "create_at", direction = DESC) Pageable pageable) {
		Page<ArticleDto> result = articleService.findArticleByTagId(tagId, pageable);
		return JsonData.success(result);
	}

	@GetMapping(value = "/{articleId}")
	public JsonData<Object> findArticleByPrimaryKey(@PathVariable String articleId) {
		Object mapJson = redisUtil.get(RedisConstant.REDIS_KEY_ARTICLE + articleId).orElse(null);
		if (mapJson == null) {
			ArticleDto articleResult = articleService.findArticleById(articleId);
			redisUtil.set(RedisConstant.REDIS_KEY_ARTICLE + articleId, articleResult, CommonConst.TIME_OUT_DAY);
			return JsonData.success(articleResult);
		}
		return JsonData.success(mapJson);
	}

	@Operation(summary = "点赞", description = "id")
	@PutMapping(value = "/like/{articleId}")
	public JsonData<Void> upVote(@PathVariable String articleId) {
		articleService.upVote(articleId);
		redisUtil.del(RedisConstant.REDIS_KEY_ARTICLE + articleId);
		return JsonData.success();
	}

	@Operation(summary = "取消点赞", description = "id")
	@DeleteMapping(value = "/like/{articleId}")
	public JsonData<Void> unUpVote(@PathVariable String articleId) {
		articleService.unUpVote(articleId);
		redisUtil.del(RedisConstant.REDIS_KEY_ARTICLE + articleId);
		return JsonData.success();
	}

	@GetMapping("/admin")
	@Operation(summary = "获取作者文章数据", description = "Admin")
	public JsonData<Map<String, Object>> findAdminInfo() {
		return JsonData.success(articleService.findAuthorDetail());
	}
}
