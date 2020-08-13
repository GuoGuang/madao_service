package com.codeway.article.controller.backstage;

import com.codeway.annotation.OptLog;
import com.codeway.article.service.backstage.CategoryService;
import com.codeway.enums.OptLogType;
import com.codeway.pojo.article.Category;
import com.codeway.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Api(tags = "文章分类管理")
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "查询全部数据", notes = "id")
    @GetMapping
    public JsonData<Page<Category>> findCategoryByCondition(Category category,
                                                                    @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
        Page<Category> result = categoryService.findCategoryByCondition(category, pageable);
        return JsonData.success(result);
    }

    @ApiOperation(value = "根据ID查询", notes = "id")
    @GetMapping(value = "/{id}")
    public JsonData<Category> findCategoryByPrimaryKey(@PathVariable String id) {
        Category result = categoryService.findCategoryById(id);
        return JsonData.success(result);
    }


    @ApiOperation(value = "添加一条新的分类", notes = "id")
    @PostMapping
    @OptLog(operationType = OptLogType.ADD, operationName = "添加文章分类")
    public JsonData<Void> insertCategory(@RequestBody @Valid Category category) {
        categoryService.saveOrUpdate(category);
        return JsonData.success();
    }

	@PutMapping
	@OptLog(operationType = OptLogType.MODIFY, operationName = "修改文章分类")
	@ApiOperation(value = "修改文章分类", notes = "id")
    public JsonData<Void> updateByCategorySelective(@RequestBody @Valid Category category) {
        categoryService.saveOrUpdate(category);
        return JsonData.success();
    }

    @DeleteMapping
//    @OptLog(operationType = OptLogType.DELETE, operationName = "删除文章分类")
    @ApiOperation(value = "删除文章分类", notes = "id")
    public JsonData<Void> deleteCategoryByIds(@RequestBody List<String> categoryIds) {
        categoryService.deleteCategoryByIds(categoryIds);
        return JsonData.success();
    }

}
