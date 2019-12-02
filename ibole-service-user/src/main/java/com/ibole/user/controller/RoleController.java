package com.ibole.user.controller;

import com.ibole.annotation.OptLog;
import com.ibole.config.CustomPageRequest;
import com.ibole.constant.CommonConst;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.user.Role;
import com.ibole.pojo.user.User;
import com.ibole.user.service.RoleService;
import com.ibole.utils.JsonData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色管理
 **/

@Api(tags = "角色")
@RestController
@RequestMapping(value = "/su/role")
public class RoleController {

	private final RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * 条件查询角色
	 *
	 * @param role 查询参数
	 * @return JsonData
	 */
	@GetMapping
	public JsonData findRuleByCondition(Role role, QueryVO queryVO,
										@RequestParam(name = "pageNum", defaultValue = "0") Integer pageNumber, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		queryVO.setPageable(new CustomPageRequest(pageNumber, pageSize));
		Page<Role> result = roleService.findRuleByCondition(role, queryVO);
		return JsonData.success(result);
	}

	/**
	 * 查询当前用户关联的角色
	 * @param role 查询参数
	 * @return JsonData
	 */
	@GetMapping("/user")
	public JsonData fetchUsersList(Role role) {
		List<User> result = roleService.findUsersOfRole(role);
		return JsonData.success(result);
	}

	/**
	 * 根据角色id查询匹配的资源
	 * @param roleId 角色id
	 * @return JsonData
	 */
	@GetMapping("/{roleId}")
	public JsonData findRoleById(@PathVariable String roleId) {
		Role result = roleService.findRoleById(roleId);
		return JsonData.success(result);
	}

	/**
	 * 添加一个角色
	 * @param role 角色实体
	 * @return JsonData
	 */
	@PostMapping
	@OptLog(operationType= CommonConst.MODIFY,operationName="添加一个角色")
	public JsonData insertSelective(@RequestBody @Valid Role role) {
		roleService.saveOrUpdate(role);
		return JsonData.success();
	}

	/**
	 * 更新角色
	 * @param role 角色实体
	 * @return JsonData
	 */
	@PutMapping()
	@OptLog(operationType= CommonConst.MODIFY,operationName="更新角色")
	public JsonData updateByPrimaryKey(@RequestBody @Valid Role role) {
		roleService.saveOrUpdate(role);
		return JsonData.success();
	}

	/**
	 * 删除角色
	 * @param roleId 角色id数组
	 * @return JsonData
	 */
	@DeleteMapping
	@OptLog(operationType= CommonConst.DELETE,operationName="删除角色")
	public JsonData deleteByIds(@RequestBody List<String> roleId) {
		roleService.deleteByIds(roleId);
		return JsonData.success();
	}
}
