package com.codeway.model.dto.user;

import com.codeway.model.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 资源实体
 **/

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResourceDto extends BasePojo implements Serializable, Cloneable {

	private Set<RoleDto> roles = new HashSet<>();

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
	private Boolean isHidden;

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

}