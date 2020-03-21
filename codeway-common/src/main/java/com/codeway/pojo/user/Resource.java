package com.codeway.pojo.user;

import com.codeway.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 资源实体
 **/
@Getter
@Setter
@Entity
@Table(name = "us_resource")
public class Resource extends BasePojo implements Serializable, Cloneable {

	@ManyToMany(mappedBy = "resources")
	@JsonIgnore
	@org.hibernate.annotations.ForeignKey(name = "none")
	private Set<Role> roles = new HashSet<>();

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.codeway.config.IdGeneratorConfig")
	@Column(name="id", unique=true, nullable=false, updatable=false, length = 20)
	@ApiModelProperty("资源表主键")
	private String id;

	@NotNull(message = "名称不能为空")
	@Pattern(regexp = "([a-zA-Z0-9\u4E00-\u9FA5]{2,10})", message = "必须是2到10位(字母，数字)名称！")
	@ApiModelProperty("名称")
	@Column(length = 20)
	private String name;

	@ApiModelProperty("父资源id")
	@Column(length = 20)
	private String parentId;

	@ApiModelProperty("vue组件名称/名称必须真实存在")
	@Column(length = 30)
	private String component;

	@ApiModelProperty("图标")
	@Column(length = 50)
	private String icon;

	@ApiModelProperty("路径")
	@Column(length = 100)
	private String path;

	@ApiModelProperty("排序")
	@Column(precision = 30, scale = 1,length = 5)
	private float sort;

	@ApiModelProperty(value = "是否隐藏",example = "1")
	@Column(length = 1)
	private Integer isHidden;

	@ApiModelProperty("描述")
	@NotNull(message = "描述不能为空")
	@Column(length = 200)
	private String description;

	@ApiModelProperty("btn 或resource")
	@Column(length = 10)
	private String type;

	@ApiModelProperty("请求url，跟path不同，path为vue的组件请求路径，url是网络请求路径")
	@Column(length = 30)
	private String url;

	@ApiModelProperty("请求方法")
	@Column(length = 20)
	private String method;

	@ApiModelProperty("资源标识")
	@Column(length = 20)
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