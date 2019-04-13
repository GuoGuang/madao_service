package com.youyd.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.base.service.MenuService;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.base.Menu;
import com.youyd.utils.JsonData;
import com.youyd.utils.StatusCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @description: 资源管理
 * @author: LGG
 * @create: 2018-12-23
 **/

@Api(tags = "菜单")
@RestController
@RequestMapping(value = "/menu")
public class MenuController {


	@Autowired
	private MenuService menuService;


	/**
	 * 条件查询菜单
	 * @param paramMap 查询参数
	 * @return JsonData
	 */
	@GetMapping
	public JsonData findResByCondition(Menu menu, QueryVO queryVO) {
		IPage<Menu> resData = menuService.findMenuByCondition(menu,queryVO);
		return new JsonData(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), resData);
	}

	/**
	 * 根据id查询单条
	 * @param resId:资源数据
	 * @return
	 */
	@GetMapping(value = "/{resId}")
	public JsonData findById(@PathVariable String resId) {
		Menu resData = menuService.findMenuById(resId);
		return new JsonData(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), resData);
	}

	/**
	 * 获取用户权限，信息
	 *
	 * @param token
	 * @return boolean
	 */
	@PostMapping("/info")
	public JsonData info(Menu menu, QueryVO queryVO) {
		IPage<Menu> menuByCondition = menuService.findMenuByCondition(menu, queryVO);
		return new JsonData(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), menu);
	}


	/**
	 * 更新资源
	 * @param resources 资源实体
	 * @return JsonData
	 */
	@PutMapping()
	public JsonData updateByPrimaryKey(@RequestBody Menu resources) {
		boolean state = menuService.updateByPrimaryKey(resources);
		return new JsonData(state, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

	/**
	 * 添加一条数据
	 * @param resources 资源实体
	 * @return JsonData
	 */
	@PostMapping
	public JsonData insertSelective(@RequestBody Menu resources) {
		boolean state = menuService.insertSelective(resources);
		return new JsonData(state, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

	/**
	 * 删除资源
	 * @param resId 资源id数组
	 * @return JsonData
	 */
	@DeleteMapping()
	public JsonData deleteByIds(@RequestBody List<String> resId) {
		boolean state = menuService.deleteByIds(resId);
		return new JsonData(state, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}
}