package com.madao.model.entity.base;

import com.madao.model.BasePojo;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Entity
@Table(name = "ba_dict",
		uniqueConstraints = @UniqueConstraint(columnNames = {"code"}),
		indexes = {
				@Index(name = "dict_code", columnList = "code"),
				@Index(name = "dict_parent_id", columnList = "parentId"),
				@Index(name = "dict_create_at", columnList = "createAt")
		})
public class Dict extends BasePojo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
	@Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
	private String id;
	@Column(length = 20)
	private String parentId;
	@Column(length = 10)
	private String code;
	@Column(length = 10)
	private String name;
	@Column(length = 200)
	private String description;
	@Column(length = 10)
	private String type;

	public String getId() {
		return id;
	}

	public Dict setId(String id) {
		this.id = id;
		return this;
	}

	public String getParentId() {
		return parentId;
	}

	public Dict setParentId(String parentId) {
		this.parentId = parentId;
		return this;
	}

	public String getCode() {
		return code;
	}

	public Dict setCode(String code) {
		this.code = code;
		return this;
	}

	public String getName() {
		return name;
	}

	public Dict setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Dict setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getType() {
		return type;
	}

	public Dict setType(String type) {
		this.type = type;
		return this;
	}
}