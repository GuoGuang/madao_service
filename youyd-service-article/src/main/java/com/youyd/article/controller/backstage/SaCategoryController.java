package com.youyd.article.controller.backstage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.article.service.backstage.SaCategoryService;
import com.youyd.enums.StatusEnum;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.article.Category;
import com.youyd.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 文章分类
 * @author: LGG
 * @create: 2019-01-11
 **/

@Api(tags = "文章分类")
@RestController
@RequestMapping(value = "/sa/article/category", produces = "application/json")
public class SaCategoryController {

	private final SaCategoryService columnService;

	@Autowired
	public SaCategoryController(SaCategoryService columnService) {
		this.columnService = columnService;
	}

	/**
	 * 查询全部数据
	 *
	 * @return JsonData
	 */
	@GetMapping
	public JsonData findCategoryByCondition(Category category, QueryVO queryVO ) {
		IPage<Category> categoryByCondition = columnService.findCategoryByCondition(category,queryVO);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), categoryByCondition);
	}

	/**
	 * 根据ID查询
	 *
	 * @param id ID
	 * @return JsonData
	 */
	@GetMapping(value = "/{id}")
	public JsonData findCategoryByPrimaryKey(@PathVariable String id) {
		Category category = columnService.findCategoryByPrimaryKey(id);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), category);
	}


	/**
	 * 增加
	 */
	@ApiOperation(value = "添加一条新的分类", notes = "id")
	@PostMapping
	public JsonData insertCategory(@RequestBody @Valid Category category) {
		columnService.insertCategory(category);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 修改
	 */
	@PutMapping
	public JsonData updateByCategorySelective(@RequestBody Category category) {
		columnService.updateByCategorySelective(category);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 删除
	 *
	 * @param categoryIds :分类id数组
	 */
	@DeleteMapping
	public JsonData deleteCategoryByIds(@RequestBody List<String> categoryIds) {
		columnService.deleteCategoryByIds(categoryIds);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

}
