package com.madao.article.controller.blog;

import com.madao.article.service.blog.ApiArticleSearchService;
import com.madao.model.dto.article.ArticleSearchDto;
import com.madao.utils.JsonData;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章搜索
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2021-11-23 07:37
 */
@RestController
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleSearchController {

	private final ApiArticleSearchService articleSearchService;

	@GetMapping(value = "/search/{keywords}/{page}/{size}")
	public JsonData<List<ArticleSearchDto>> searchArticleByCondition(@PathVariable String keywords, @PathVariable Integer page, @PathVariable Integer size) {
		List<ArticleSearchDto> articles = articleSearchService.searchArticleByCondition(keywords, page, size);
		return JsonData.success(articles);
	}

	@GetMapping(value = "/{id}")
	public JsonData<ArticleSearchDto> findArticleByPrimaryKey(@PathVariable String id) {
		ArticleSearchDto result = articleSearchService.findArticleByPrimaryKey(id);
		return JsonData.success(result);
	}

	@PostMapping
	public JsonData<Void> insertArticle(@RequestBody ArticleSearchDto article) {
		articleSearchService.insertArticle(article);
		return JsonData.success(null);
	}

	@PutMapping(value = "/{id}")
	public JsonData<Void> updateByPrimaryKeySelective(@RequestBody ArticleSearchDto article, @PathVariable String id) {
		article.setId(id);
		articleSearchService.updateByPrimaryKey(article);
		return JsonData.success(null);
	}

	@DeleteMapping(value = "/{id}")
	public JsonData<Void> delete(@PathVariable String id) {
		articleSearchService.deleteById(id);
		return JsonData.success(null);
	}

}
