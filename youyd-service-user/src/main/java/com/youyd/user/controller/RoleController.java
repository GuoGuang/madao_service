package com.youyd.user.controller;

import com.youyd.user.pojo.Role;
import com.youyd.user.service.RoleService;
import com.youyd.utils.JsonData;
import com.youyd.utils.StatusCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description: 角色管理
 * @author: LGG
 * @create: 2018-12-23
 **/

@Api(tags = "角色")
@RestController
@RequestMapping(value = "/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	/**
	 * 条件查询角色
	 * @param paramMap 查询参数
	 * @return JsonData
	 */
	@GetMapping
	public JsonData findRuleByCondition(@RequestParam Map paramMap) {
		List ruleData = roleService.findRuleByCondition(paramMap);
		return new JsonData(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), ruleData);
	}

	/**
	 * 根据用户id查询
	 * @param roleId 角色id
	 * @return JsonData
	 */
	@GetMapping("/{roleId}")
	public JsonData findById(@PathVariable String roleId) {
		Role ruleData = roleService.findRuleById(roleId);
		return new JsonData(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), ruleData);
	}

	/**
	 * 添加一个角色
	 * @param role 角色实体
	 * @return JsonData
	 */
	@PostMapping
	public JsonData insertSelective(@RequestBody Role role) {
		boolean state = roleService.insertSelective(role);
		return new JsonData(state, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

	/**
	 * 删除角色
	 * @param roleId 角色id数组
	 * @return JsonData
	 */
	@DeleteMapping()
	public JsonData deleteByIds(@RequestBody List roleId) {
		boolean state = roleService.deleteByIds(roleId);
		return new JsonData(state, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

	/**
	 * 更新角色
	 * @param role 角色实体
	 * @return JsonData
	 */
	@PutMapping()
	public JsonData updateByPrimaryKey(@RequestBody Role role) {
		boolean state = roleService.updateByPrimaryKey(role);
		return new JsonData(state, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

	/**
	 * 更新角色状态
	 * @param roleIds id数组
	 * @return JsonData
	 */
	@PutMapping("/state")
	public JsonData updateRoleState(@RequestBody List roleIds){
		roleService.updateRoleState(roleIds);
		return new JsonData(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), null);
	}

}
