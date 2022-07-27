package com.madao.user.controller;

import com.madao.annotation.OptLog;
import com.madao.enums.OptLogType;
import com.madao.model.QueryVO;
import com.madao.model.dto.user.RoleDto;
import com.madao.model.entity.user.Role;
import com.madao.user.service.RoleService;
import com.madao.utils.JsonData;
import com.querydsl.core.QueryResults;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Tag(name = "角色管理")
@RestController
@RequestMapping(value = "/role", produces = "application/json")
@AllArgsConstructor
public class RoleController {

	private final RoleService roleService;

	@GetMapping
	@Operation(summary = "条件查询角色", description = "Role")
	public JsonData<QueryResults<Role>> findRuleByCondition(RoleDto roleDto, QueryVO queryVO) {
		QueryResults<Role> result = roleService.findRuleByCondition(roleDto, queryVO);
		return JsonData.success(result);
	}

	@GetMapping("/user/{id}")
	@Operation(summary = "查询用户角色组", description = "Dict")
	public JsonData<List<RoleDto>> getUseRoles(@PathVariable String id) {
		List<RoleDto> role = roleService.getUseRoles(id);
		return JsonData.success(role);
	}

	@GetMapping("/{roleId}")
	@Operation(summary = "根据角色id查询匹配的资源", description = "Role")
	public JsonData<RoleDto> findRoleById(@PathVariable String roleId) {
		RoleDto result = roleService.findRoleById(roleId);
		return JsonData.success(result);
	}

	@PostMapping
	@OptLog(operationType = OptLogType.MODIFY, operationName = "添加一个角色")
	@Operation(summary = "添加一个角色", description = "Role")
	public JsonData<Void> insertSelective(@RequestBody @Validated RoleDto roleDto) {
		roleService.saveOrUpdate(roleDto);
		return JsonData.success();
	}

	@PutMapping()
	@OptLog(operationType = OptLogType.MODIFY, operationName = "更新角色")
	@Operation(summary = "更新角色", description = "Role")
	public JsonData<Void> updateByPrimaryKey(@RequestBody @Validated RoleDto roleDto) {
		roleService.saveOrUpdate(roleDto);
		return JsonData.success();
	}

	@DeleteMapping
	@OptLog(operationType = OptLogType.DELETE, operationName = "删除角色")
	@Operation(summary = "删除角色", description = "Role")
	public JsonData<Void> deleteByIds(@RequestBody List<String> roleId) {
		roleService.deleteByIds(roleId);
		return JsonData.success();
	}
}
