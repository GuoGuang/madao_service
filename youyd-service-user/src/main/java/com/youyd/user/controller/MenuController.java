package com.youyd.user.controller;

import com.youyd.user.pojo.Menu;
import com.youyd.user.service.MenuService;
import com.youyd.utils.JSONData;
import com.youyd.utils.StatusCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


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
	 * 根据id查询单条
	 * @param resId:资源数据
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public JSONData findById(@PathVariable String resId) {
		Menu resData = menuService.findMenuById(resId);
		return new JSONData(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), resData);
	}

	/**
	 * 条件查询资源
	 * @param paramMap 查询参数
	 * @return JSONData
	 */
	@GetMapping
	public JSONData findResByCondition(@RequestParam Map paramMap) {
		List resData = menuService.findMenuByCondition("");
		return new JSONData(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), resData);
	}


	/**
	 * 更新资源
	 * @param resources 资源实体
	 * @return JSONData
	 */
	@PutMapping()
	public JSONData updateByPrimaryKey(@RequestBody Menu resources) {
		boolean state = menuService.updateByPrimaryKey(resources);
		return new JSONData(state, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

	/**
	 * 添加一条数据
	 * @param resources 资源实体
	 * @return JSONData
	 */
	@PostMapping
	public JSONData insertSelective(@RequestBody Menu resources) {
		boolean state = menuService.insertSelective(resources);
		return new JSONData(state, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

	/**
	 * 删除资源
	 * @param resId 资源id数组
	 * @return JSONData
	 */
	@DeleteMapping()
	public JSONData deleteByIds(@RequestBody List resId) {
		boolean state = menuService.deleteByIds(resId);
		return new JSONData(state, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}
}