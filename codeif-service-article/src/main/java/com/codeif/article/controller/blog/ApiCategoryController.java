package com.codeif.article.controller.blog;

import com.codeif.article.service.backstage.CategoryService;
import com.codeif.pojo.QueryVO;
import com.codeif.pojo.article.Category;
import com.codeif.utils.JsonData;
import com.querydsl.core.QueryResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "前台分类")
@RestController
@RequestMapping(value = "/api/ar/category")
public class ApiCategoryController {

	@Autowired
	private CategoryService categoryService;

    @ApiOperation(value = "查询分类集合", notes = "Category")
    @GetMapping
    public JsonData<QueryResults<Category>> findCategoryByCondition(Category category, QueryVO queryVO) {
	    QueryResults<Category> categoryByCondition = categoryService.findCategoryByCondition(category, queryVO);
        return JsonData.success(categoryByCondition);
    }

    @ApiOperation(value = "查询分类id集合", notes = "Category")
    @GetMapping("/{id}")
    public JsonData<Category> findCategoryById(@PathVariable String id) {
	    Category result = categoryService.findCategoryById(id);
        return JsonData.success(result);
    }
}
