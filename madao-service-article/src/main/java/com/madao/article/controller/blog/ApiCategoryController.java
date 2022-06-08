package com.madao.article.controller.blog;

import com.madao.article.service.backstage.CategoryService;
import com.madao.model.dto.article.CategoryDto;
import com.madao.utils.JsonData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Tag(name = "前台分类")
@RestController
@RequestMapping(value = "/api/ar/category")
public class ApiCategoryController {

	private final CategoryService categoryService;

	public ApiCategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Operation(summary = "查询分类集合", description = "Category")
	@GetMapping
	public JsonData<Page<CategoryDto>> findCategoryByCondition(CategoryDto categoryDto,
	                                                           @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
		Page<CategoryDto> categoryByCondition = categoryService.findCategoryByCondition(categoryDto, pageable);
		return JsonData.success(categoryByCondition);
	}

	@Operation(summary = "根据id查询分类详情", description = "Category")
	@GetMapping("/{id:\\d+}")
	public JsonData<CategoryDto> findCategoryById(@PathVariable String id) {
		CategoryDto result = categoryService.findCategoryById(id);
		return JsonData.success(result);
	}
}
