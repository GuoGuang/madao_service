package com.codeif.user.controller;

import com.codeif.annotation.OptLog;
import com.codeif.constant.CommonConst;
import com.codeif.pojo.QueryVO;
import com.codeif.pojo.user.Role;
import com.codeif.pojo.user.User;
import com.codeif.user.service.RoleService;
import com.codeif.utils.JsonData;
import com.querydsl.core.QueryResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "角色管理")
@RestController
@RequestMapping(value = "/role")
public class RoleController {

	private final RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

    @GetMapping
    @ApiOperation(value = "条件查询角色", notes = "Role")
    public JsonData<QueryResults<Role>> findRuleByCondition(Role role, QueryVO queryVO) {
        QueryResults<Role> result = roleService.findRuleByCondition(role, queryVO);
        return JsonData.success(result);
    }

    @GetMapping("/user")
    @ApiOperation(value = "查询当前用户关联的角色", notes = "Role")
    public JsonData<List<User>> fetchUsersList(Role role) {
        List<User> result = roleService.findUsersOfRole(role);
        return JsonData.success(result);
    }

    @GetMapping("/{roleId}")
    @ApiOperation(value = "根据角色id查询匹配的资源", notes = "Role")
    public JsonData<Role> findRoleById(@PathVariable String roleId) {
        Role result = roleService.findRoleById(roleId);
        return JsonData.success(result);
    }

    @PostMapping
    @OptLog(operationType = CommonConst.MODIFY, operationName = "添加一个角色")
    @ApiOperation(value = "添加一个角色", notes = "Role")
    public JsonData<Void> insertSelective(@RequestBody @Valid Role role) {
        roleService.saveOrUpdate(role);
        return JsonData.success();
    }

    @PutMapping()
    @OptLog(operationType = CommonConst.MODIFY, operationName = "更新角色")
    @ApiOperation(value = "更新角色", notes = "Role")
    public JsonData<Void> updateByPrimaryKey(@RequestBody @Valid Role role) {
        roleService.saveOrUpdate(role);
        return JsonData.success();
    }

    @DeleteMapping
    @OptLog(operationType = CommonConst.DELETE, operationName = "删除角色")
    @ApiOperation(value = "删除角色", notes = "Role")
    public JsonData<Void> deleteByIds(@RequestBody List<String> roleId) {
        roleService.deleteByIds(roleId);
        return JsonData.success();
    }
}
