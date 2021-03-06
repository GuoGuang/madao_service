package com.madao.model.pojo.user;

import com.madao.model.BasePojo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
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

    @Column(precision = 30, scale = 1, length = 5)
    private float sort;

    @Column
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

	public String getId() {
		return id;
	}

	public Resource setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Resource setName(String name) {
		this.name = name;
		return this;
	}

	public String getParentId() {
		return parentId;
	}

	public Resource setParentId(String parentId) {
		this.parentId = parentId;
		return this;
	}

	public String getComponent() {
		return component;
	}

	public Resource setComponent(String component) {
		this.component = component;
		return this;
	}

	public String getIcon() {
		return icon;
	}

	public Resource setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public String getPath() {
		return path;
	}

	public Resource setPath(String path) {
		this.path = path;
		return this;
	}

	public float getSort() {
		return sort;
	}

	public Resource setSort(float sort) {
		this.sort = sort;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Resource setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getType() {
		return type;
	}

	public Resource setType(String type) {
		this.type = type;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public Resource setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getMethod() {
		return method;
	}

	public Resource setMethod(String method) {
		this.method = method;
		return this;
	}

	public String getCode() {
		return code;
	}

	public Resource setCode(String code) {
		this.code = code;
		return this;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public Resource setHidden(Boolean hidden) {
		this.hidden = hidden;
		return this;
	}
}