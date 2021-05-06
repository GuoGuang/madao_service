package com.madao.model.dto.user;

import com.google.common.base.Objects;
import com.madao.model.BasePojo;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RoleDto extends BasePojo implements Serializable {

    @ApiModelProperty("角色关联的资源")
    private Set<ResourceDto> resources = new HashSet<>();

    private Set<UserDto> users = new HashSet<>();

    private String id;

    @NotNull(message = "角色名称不能为空")
    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色描述")
    private String roleDesc;

    @NotNull(message = "角色编码不能为空")
    @ApiModelProperty("角色编码")
    private String code;

	public Set<ResourceDto> getResources() {
		return resources;
	}

	public RoleDto setResources(Set<ResourceDto> resources) {
		this.resources = resources;
		return this;
	}

	public Set<UserDto> getUsers() {
		return users;
	}

	public RoleDto setUsers(Set<UserDto> users) {
		this.users = users;
		return this;
	}

	public String getId() {
		return id;
	}

	public RoleDto setId(String id) {
		this.id = id;
		return this;
	}

	public String getRoleName() {
		return roleName;
	}

	public RoleDto setRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public RoleDto setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
		return this;
	}

	public String getCode() {
		return code;
	}

	public RoleDto setCode(String code) {
		this.code = code;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof RoleDto)) return false;
		if (!super.equals(o)) return false;
		RoleDto roleDto = (RoleDto) o;
		return Objects.equal(resources, roleDto.resources) && Objects.equal(users, roleDto.users) && Objects.equal(id, roleDto.id) && Objects.equal(roleName, roleDto.roleName) && Objects.equal(roleDesc, roleDto.roleDesc) && Objects.equal(code, roleDto.code);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), resources, users, id, roleName, roleDesc, code);
	}
}