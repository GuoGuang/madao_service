package com.madao.model.entity.user;

import com.madao.model.BasePojo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Getter
@Setter
@Entity
@Table(name = "us_resource",
		indexes = {
				@Index(name = "resource_type", columnList = "type"),
				@Index(name = "resource_code", columnList = "code"),
				@Index(name = "resource_create_at", columnList = "createAt")
		})
public class Resource extends BasePojo implements Serializable, Cloneable {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
	@Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
	private String id;

	@Column(length = 20)
	private String name;

	@Column(length = 20)
	private String parentId;

	@Column(length = 30)
	private String component;

	@Column(length = 50)
	private String icon;

	@Column(length = 100)
	private String path;

	@Column( length = 5)
	private float sort;

	@Column(length = 1, columnDefinition = "bit(1) COMMENT '是否隐藏' default '0'")
	private Boolean hidden;

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

	@Column(length = 20)
	private String method;

	@Column(length = 20)
	private String code;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}