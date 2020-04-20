package com.codeway.article.controller.blog;

import com.codeway.article.service.backstage.CategoryService;
import com.codeway.pojo.QueryVO;
import com.codeway.pojo.article.Category;
import com.codeway.utils.JsonData;
import com.querydsl.core.QueryResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private CategoryService categoryService;

    @ApiOperation(value = "查询分类集合", notes = "Category")
    @GetMapping
    public JsonData<Page<Category>> findCategoryByCondition(Category category,
                                                                    @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
	    Page<Category> categoryByCondition = categoryService.findCategoryByCondition(category, pageable);
        return JsonData.success(categoryByCondition);
    }

    @ApiOperation(value = "查询分类id集合", notes = "Category")
    @GetMapping("/{id}")
    public JsonData<Category> findCategoryById(@PathVariable String id) {
	    Category result = categoryService.findCategoryById(id);
        return JsonData.success(result);
    }
}
