package com.madao.model.dto.user;

import com.google.common.base.Objects;
import com.madao.model.BasePojo;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 资源实体
 **/


public class ResourceDto extends BasePojo implements Serializable, Cloneable {

    private Set<com.madao.model.dto.user.RoleDto> roles = new HashSet<>();

    @ApiModelProperty("资源表主键")
    private String id;

    @NotNull(message = "名称不能为空")
    @Pattern(regexp = "([a-zA-Z0-9\u4E00-\u9FA5]{2,10})", message = "必须是2到10位(字母，数字)名称！")
    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("父资源id")
    private String parentId;

    @ApiModelProperty("vue组件名称/名称必须真实存在")
    private String component;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("排序")
    private float sort;

	@ApiModelProperty(value = "是否隐藏", example = "1")
    private Boolean hidden;

    @ApiModelProperty("描述")
    @NotNull(message = "描述不能为空")
    private String description;

    @ApiModelProperty("btn 或resource")
    private String type;

    @ApiModelProperty("请求url，跟path不同，path为vue的组件请求路径，url是网络请求路径")
    private String url;

    @ApiModelProperty("请求方法")
    private String method;

    @ApiModelProperty("资源标识")
    private String code;

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public ResourceDto setRoles(Set<RoleDto> roles) {
		this.roles = roles;
		return this;
	}

	public String getId() {
		return id;
	}

	public ResourceDto setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public ResourceDto setName(String name) {
		this.name = name;
		return this;
	}

	public String getParentId() {
		return parentId;
	}

	public ResourceDto setParentId(String parentId) {
		this.parentId = parentId;
		return this;
	}

	public String getComponent() {
		return component;
	}

	public ResourceDto setComponent(String component) {
		this.component = component;
		return this;
	}

	public String getIcon() {
		return icon;
	}

	public ResourceDto setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public String getPath() {
		return path;
	}

	public ResourceDto setPath(String path) {
		this.path = path;
		return this;
	}

	public float getSort() {
		return sort;
	}

	public ResourceDto setSort(float sort) {
		this.sort = sort;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public ResourceDto setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getType() {
		return type;
	}

	public ResourceDto setType(String type) {
		this.type = type;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public ResourceDto setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getMethod() {
		return method;
	}

	public ResourceDto setMethod(String method) {
		this.method = method;
		return this;
	}

	public String getCode() {
		return code;
	}

	public ResourceDto setCode(String code) {
		this.code = code;
		return this;
	}
	public Boolean getHidden() {
		return hidden;
	}

	public ResourceDto setHidden(Boolean hidden) {
		this.hidden = hidden;
		return this;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ResourceDto)) return false;
		if (!super.equals(o)) return false;
		ResourceDto that = (ResourceDto) o;
		return Float.compare(that.sort, sort) == 0 && Objects.equal(roles, that.roles) && Objects.equal(id, that.id) && Objects.equal(name, that.name) && Objects.equal(parentId, that.parentId) && Objects.equal(component, that.component) && Objects.equal(icon, that.icon) && Objects.equal(path, that.path) && Objects.equal(hidden, that.hidden) && Objects.equal(description, that.description) && Objects.equal(type, that.type) && Objects.equal(url, that.url) && Objects.equal(method, that.method) && Objects.equal(code, that.code);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), roles, id, name, parentId, component, icon, path, sort, hidden, description, type, url, method, code);
	}
}