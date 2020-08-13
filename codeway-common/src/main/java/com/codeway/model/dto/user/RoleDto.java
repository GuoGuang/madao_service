package com.codeway.model.dto.user;

import com.codeway.model.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleDto extends BasePojo implements Serializable {

	@ApiModelProperty("角色关联的资源")
	private Set<ResourceDto> resources = new HashSet<>();

	@ManyToMany(mappedBy = "roles")
	private Set<UserDto> users = new HashSet<>();

	private String id;

	@NotNull(message = "角色名称不能为空")
	@ApiModelProperty("角色名称")
	private String roleName;

	@ApiModelProperty("角色描述")
	private String roleDesc;

	@NotNull(message = "角色编码不能为空")
	@ApiModelProperty("角色编码")
	private String roleCode;

}