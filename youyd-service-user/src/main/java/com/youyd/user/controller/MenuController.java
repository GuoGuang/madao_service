package com.youyd.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.enums.StatusEnum;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.base.Menu;
import com.youyd.user.service.MenuService;
import com.youyd.utils.DateUtil;
import com.youyd.utils.JsonData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 *  资源管理
 * @author : LGG
 * @create : 2018-12-23
 **/

@Api(tags = "菜单")
@RestController
@RequestMapping(value = "/su/user/menu")
public class MenuController {


	private final MenuService menuService;

	@Autowired
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}


	/**
	 * 条件查询菜单
	 * @param 菜单实体 查询参数
	 * @param queryVO 查询参数
	 * @return JsonData
	 */
	@GetMapping
	public JsonData findResByCondition(Menu menu, QueryVO queryVO) {
		IPage<Menu> resData = menuService.findMenuByCondition(menu,queryVO);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), resData);
	}

	/**
	 * 根据id查询单条
	 * @param resId:资源数据
	 * @return  JsonData
	 */
	@GetMapping(value = "/{id}")
	public JsonData findById(@PathVariable String id) {
		Menu resData = menuService.findMenuById(id);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), resData);
	}

	/**
	 * 获取用户权限，信息
	 * @param token
	 * @return boolean
	 */
	@PostMapping("/info")
	public JsonData info(Menu menu,QueryVO queryVO ) {
		IPage<Menu> menuByCondition = menuService.findMenuByCondition(menu,queryVO);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), menuByCondition);
	}


	/**
	 * 更新资源
	 * @param  menu 菜单
	 * @return JsonData
	 */
	@PutMapping
	public JsonData updateByPrimaryKey(@RequestBody Menu menu) {
		boolean state = menuService.updateByPrimaryKey(menu);
		return new JsonData(state, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 添加一条数据
	 * @param menu 菜单
	 * @return JsonData
	 */
	@PostMapping
	public JsonData insertSelective(@RequestBody Menu menu) {
		menu.setCreateAt(DateUtil.getTimestamp());
		menu.setUpdateAt(DateUtil.getTimestamp());
		boolean state = menuService.insertSelective(menu);
		return new JsonData(state, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 删除资源
	 * @param resId 资源id数组
	 * @return JsonData
	 */
	@DeleteMapping()
	public JsonData deleteByIds(@RequestBody List<String> resId) {
		boolean state = menuService.deleteByIds(resId);
		return new JsonData(state, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}
}