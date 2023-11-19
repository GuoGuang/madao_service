package com.madao.article.controller.blog;

import com.madao.article.service.blog.ApiArticleSearchService;
import com.madao.model.dto.article.ArticleSearchDto;
import com.madao.utils.JsonData;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

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

	@PostMapping(value = "/search")
	public JsonData<Page<ArticleSearchDto>> searchArticleByCondition(@RequestBody ArticleSearchDto articleSearchDto, @PageableDefault(sort = "topDate", direction = DESC) Pageable pageable) {
		Page<ArticleSearchDto> articles = articleSearchService.searchByDtoAndPage(articleSearchDto, pageable);
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
		return JsonData.success();
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
