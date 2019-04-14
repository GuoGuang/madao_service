package com.youyd.article.controller.backstage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.article.pojo.Category;
import com.youyd.article.service.backstage.CategoryService;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.Result;
import com.youyd.utils.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(value = "/sa/category", produces = "application/json")
public class CategoryController {

	@Autowired
	private CategoryService columnService;

	/**
	 * 查询全部数据
	 *
	 * @return
	 */
	@GetMapping
	public Result findCategoryByCondition(Category category, QueryVO queryVO) {
		IPage<Category> categoryByCondition = columnService.findCategoryByCondition(category,queryVO);
		return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), categoryByCondition);
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
	 */
	@ApiOperation(value = "添加一条新的分类", notes = "id")
	@PostMapping
	public Result insertCategory(@RequestBody Category category) {
		columnService.insertCategory(category);
		return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

	/**
	 * 修改
	 */
	@PutMapping
	public Result updateByCategorySelective(@RequestBody Category category) {
		columnService.updateByCategorySelective(category);
		return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

	/**
	 * 删除
	 *
	 * @param categoryIds :分类id数组
	 */
	@DeleteMapping
	public Result deleteByPrimaryKey(@RequestBody List<Long> categoryIds) {
		columnService.deleteByPrimaryKey(categoryIds);
		return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

}
