package com.codeif.pojo.user;

import com.codeif.pojo.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

/**
 * 资源实体
 **/
@Getter
@Setter
@Entity
@Table(name = "us_resource")
public class Resource extends BasePojo implements Serializable, Cloneable {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.codeif.config.IdGeneratorConfig")
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
	@Column(precision = 30, scale = 1)
	private float sort;

	@ApiModelProperty("是否隐藏")
	private Integer isHidden;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Resource resource = (Resource) o;
		return id.equals(resource.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}