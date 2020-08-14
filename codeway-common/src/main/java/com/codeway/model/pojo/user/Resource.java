package com.codeway.model.pojo.user;

import com.codeway.model.BasePojo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "us_resource",
		indexes = {
				@Index(name = "resource_type", columnList = "type"),
				@Index(name = "resource_code", columnList = "code"),
				@Index(name = "resource_create_at", columnList = "createAt")
		})
public class Resource extends BasePojo implements Serializable, Cloneable {

	@ManyToMany(mappedBy = "resources")
	@JsonIgnore
	@org.hibernate.annotations.ForeignKey(name = "none")
	private Set<Role> roles = new HashSet<>();

	/**
	 * 资源表主键
	 */
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.codeway.config.IdGeneratorConfig")
	@Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
	private String id;

	/**
	 * 名称
	 */
	@Column(length = 20)
	private String name;

	/**
	 * 父资源id
	 */
	@Column(length = 20)
	private String parentId;

	/**
	 * vue组件名称/名称必须真实存在
	 */
	@Column(length = 30)
	private String component;

	/**
	 * 图标
	 */
	@Column(length = 50)
	private String icon;

	/**
	 * 路径
	 */
	@Column(length = 100)
	private String path;

	/**
	 * 排序
	 */
	@Column(precision = 30, scale = 1, length = 5)
	private float sort;

	/**
	 * 是否隐藏
	 */
	@Column(length = 1)
	private Integer isHidden;

	/**
	 * 描述
	 */
	@Column(length = 200)
	private String description;

	/**
	 * btn 或resource
	 */
	@Column(length = 10)
	private String type;

	/**
	 * 请求url，跟path不同，path为vue的组件请求路径，url是网络请求路径
	 */
	@Column(length = 30)
	private String url;

	/**
	 * 请求方法
	 */
	@Column(length = 20)
	private String method;

	/**
	 * 资源标识
	 */
	@Column(length = 20)
	private String code;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}