package com.madao.model.dto.user;

import com.google.common.base.Objects;
import com.madao.model.BasePojo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ResourceDto extends BasePojo implements Serializable, Cloneable {

	private Set<RoleDto> roles = new HashSet<>();

	@Schema(title = "资源表主键")
	private String id;

	@NotNull(message = "名称不能为空")
	@Pattern(regexp = "([a-zA-Z0-9\u4E00-\u9FA5]{2,10})", message = "必须是2到10位(字母，数字)名称！")
	@Schema(title = "名称")
	private String name;

	@Schema(title = "父资源id")
	private String parentId;

	@Schema(title = "vue组件名称/名称必须真实存在")
	private String component;

	@Schema(title = "图标")
	private String icon;

	@Schema(title = "路径")
	private String path;

	@Schema(title = "排序")
	private float sort;

	@Schema(title = "是否隐藏")
	private Boolean hidden;

	@Schema(title = "描述")
	@NotNull(message = "描述不能为空")
	private String description;

	@Schema(title = "btn 或resource")
	private String type;

	@Schema(title = "请求url，跟path不同，path为vue的组件请求路径，url是网络请求路径")
	private String url;

	@Schema(title = "请求方法")
	private String method;

	@Schema(title = "资源标识")
	private String code;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ResourceDto that)) return false;
		if (!super.equals(o)) return false;
		return Float.compare(that.sort, sort) == 0 && Objects.equal(roles, that.roles) && Objects.equal(id, that.id) && Objects.equal(name, that.name) && Objects.equal(parentId, that.parentId) && Objects.equal(component, that.component) && Objects.equal(icon, that.icon) && Objects.equal(path, that.path) && Objects.equal(hidden, that.hidden) && Objects.equal(description, that.description) && Objects.equal(type, that.type) && Objects.equal(url, that.url) && Objects.equal(method, that.method) && Objects.equal(code, that.code);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), roles, id, name, parentId, component, icon, path, sort, hidden, description, type, url, method, code);
	}
}