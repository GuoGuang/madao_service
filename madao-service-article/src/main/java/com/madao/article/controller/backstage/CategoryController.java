package com.madao.article.controller.backstage;

import com.madao.annotation.OptLog;
import com.madao.article.service.backstage.CategoryService;
import com.madao.enums.OptLogType;
import com.madao.model.dto.article.CategoryDto;
import com.madao.utils.JsonData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Tag(name = "文章分类管理")
@RestController
@RequestMapping(value = "/category")
@AllArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@Operation(summary = "查询全部数据", description = "id")
	@GetMapping
	public JsonData<Page<CategoryDto>> findCategoryByCondition(CategoryDto categoryDto,
	                                                           @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
		Page<CategoryDto> result = categoryService.findCategoryByCondition(categoryDto, pageable);
		return JsonData.success(result);
	}

	@Operation(summary = "根据ID查询", description = "id")
	@GetMapping(value = "/{id}")
	public JsonData<CategoryDto> findCategoryByPrimaryKey(@PathVariable String id) {
		CategoryDto result = categoryService.findCategoryById(id);
		return JsonData.success(result);
	}


	@Operation(summary = "添加一条新的分类", description = "id")
	@PostMapping
	@OptLog(operationType = OptLogType.ADD, operationName = "添加文章分类")
	public JsonData<Void> insertCategory(@RequestBody @Validated CategoryDto categoryDto) {
		categoryService.saveOrUpdate(categoryDto);
		return JsonData.success();
	}

	@PutMapping
	@OptLog(operationType = OptLogType.MODIFY, operationName = "修改文章分类")
	@Operation(summary = "修改文章分类", description = "id")
	public JsonData<Void> updateByCategorySelective(@RequestBody @Validated CategoryDto categoryDto) {
		categoryService.saveOrUpdate(categoryDto);
		return JsonData.success();
	}

	@DeleteMapping
//    @OptLog(operationType = OptLogType.DELETE, operationName = "删除文章分类")
	@Operation(summary = "删除文章分类", description = "id")
	public JsonData<Void> deleteCategoryByIds(@RequestBody List<String> categoryIds) {
		categoryService.deleteCategoryByIds(categoryIds);
		return JsonData.success();
	}

}
