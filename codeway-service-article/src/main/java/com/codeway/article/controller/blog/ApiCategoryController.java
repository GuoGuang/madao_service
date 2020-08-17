package com.codeway.article.controller.blog;

import com.codeway.article.service.backstage.CategoryService;
import com.codeway.model.dto.article.CategoryDto;
import com.codeway.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Api(tags = "前台分类")
@RestController
@RequestMapping(value = "/api/ar/category")
public class ApiCategoryController {

	private final CategoryService categoryService;

    public ApiCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

	@ApiOperation(value = "查询分类集合", notes = "Category")
	@GetMapping
	public JsonData<Page<CategoryDto>> findCategoryByCondition(CategoryDto categoryDto,
	                                                           @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
		Page<CategoryDto> categoryByCondition = categoryService.findCategoryByCondition(categoryDto, pageable);
		return JsonData.success(categoryByCondition);
	}

	@ApiOperation(value = "根据id查询分类详情", notes = "Category")
	@GetMapping("/{id:\\d+}")
	public JsonData<CategoryDto> findCategoryById(@PathVariable String id) {
		CategoryDto result = categoryService.findCategoryById(id);
		return JsonData.success(result);
	}
}
