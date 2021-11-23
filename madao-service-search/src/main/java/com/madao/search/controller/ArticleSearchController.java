package com.madao.search.controller;

import com.madao.search.pojo.Article;
import com.madao.search.service.ArticleSearchService;
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

	private final ArticleSearchService articleSearchService;

	@GetMapping(value = "/search/{keywords}/{page}/{size}")
	public JsonData<List<Article>> searchArticleByCondition(@PathVariable String keywords, @PathVariable Integer page, @PathVariable Integer size) {
		List<Article> articles = articleSearchService.searchArticleByCondition(keywords, page, size);
		return JsonData.success(articles);
	}

	@GetMapping(value = "/{id}")
	public JsonData<Article> findArticleByPrimaryKey(@PathVariable String id) {
		Article result = articleSearchService.findArticleByPrimaryKey(id);
		return JsonData.success(result);
	}

	@PostMapping
	public JsonData<Void> insertArticle(@RequestBody Article article) {
		articleSearchService.insertArticle(article);
		return JsonData.success(null);
	}

	@PutMapping(value = "/{id}")
	public JsonData<Void> updateByPrimaryKeySelective(@RequestBody Article article, @PathVariable String id) {
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
