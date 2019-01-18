package com.youyd.article.controller;

import com.youyd.article.pojo.Category;
import com.youyd.article.service.CategoryService;
import com.youyd.pojo.Result;
import com.youyd.utils.StatusCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 文章分类
 * @author: LGG
 * @create: 2019-01-11
 **/

@Api(tags = "文章分类")
@RestController
@RequestMapping(value = "/category", produces = "application/json")
public class CategoryController {

	@Autowired
	private CategoryService columnService;


	/**
	 * 查询全部数据
	 *
	 * @return
	 */
	@GetMapping
	public Result findCategoryByCondition() {
		List result = columnService.findCategoryByCondition();
		return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), result);
	}

	/**
	 * 根据ID查询
	 *
	 * @param id ID
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public Result findCategoryByPrimaryKey(@PathVariable String id) {
		Category category = columnService.findCategoryByPrimaryKey(id);
		return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), category);
	}


	/**
	 * 增加
	 *
	 * @param category
	 */
	@PostMapping
	public Result insertCategory(@RequestBody Category category) {
		columnService.insertCategory(category);
		return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

	/**
	 * 修改
	 *
	 * @param category
	 */
	@PutMapping
	public Result updateByCategorySelective(@RequestBody Category category) {
		columnService.updateByCategorySelective(category);
		return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

	/**
	 * 删除
	 *
	 * @param categoryIds
	 */
	@DeleteMapping
	public Result deleteByPrimaryKey(List categoryIds) {
		columnService.deleteByPrimaryKey(categoryIds);
		return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

}
