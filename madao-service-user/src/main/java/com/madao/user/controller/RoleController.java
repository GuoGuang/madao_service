package com.madao.user.controller;

import com.madao.annotation.OptLog;
import com.madao.enums.OptLogType;
import com.madao.model.QueryVO;
import com.madao.model.dto.user.RoleDto;
import com.madao.model.pojo.user.Role;
import com.madao.user.service.RoleService;
import com.madao.utils.JsonData;
import com.querydsl.core.QueryResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色管理")
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    @ApiOperation(value = "条件查询角色", notes = "Role")
    public JsonData<QueryResults<Role>> findRuleByCondition(RoleDto roleDto, QueryVO queryVO) {
        QueryResults<Role> result = roleService.findRuleByCondition(roleDto, queryVO);
        return JsonData.success(result);
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "查询用户角色组", notes = "Dict")
    public JsonData<List<RoleDto>> getUseRoles(@PathVariable String id) {
        List<RoleDto> role = roleService.getUseRoles(id);
        return JsonData.success(role);
    }

    @GetMapping("/{roleId}")
    @ApiOperation(value = "根据角色id查询匹配的资源", notes = "Role")
    public JsonData<RoleDto> findRoleById(@PathVariable String roleId) {
        RoleDto result = roleService.findRoleById(roleId);
        return JsonData.success(result);
    }

    @PostMapping
    @OptLog(operationType = OptLogType.MODIFY, operationName = "添加一个角色")
    @ApiOperation(value = "添加一个角色", notes = "Role")
    public JsonData<Void> insertSelective(@RequestBody @Validated RoleDto roleDto) {
        roleService.saveOrUpdate(roleDto);
        return JsonData.success();
    }

    @PutMapping()
    @OptLog(operationType = OptLogType.MODIFY, operationName = "更新角色")
    @ApiOperation(value = "更新角色", notes = "Role")
    public JsonData<Void> updateByPrimaryKey(@RequestBody @Validated RoleDto roleDto) {
        roleService.saveOrUpdate(roleDto);
        return JsonData.success();
    }

    @DeleteMapping
    @OptLog(operationType = OptLogType.DELETE, operationName = "删除角色")
    @ApiOperation(value = "删除角色", notes = "Role")
    public JsonData<Void> deleteByIds(@RequestBody List<String> roleId) {
        roleService.deleteByIds(roleId);
        return JsonData.success();
    }
}
