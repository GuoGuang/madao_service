package com.madao.model.entity.article;

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
@Table(name = "ar_category",
		uniqueConstraints = @UniqueConstraint(columnNames = {"name"}),
		indexes = {
				@Index(name = "categories_name", columnList = "name"),
				@Index(name = "categories_parent_id", columnList = "parentId")
		})
public class Category extends BasePojo implements Serializable {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
	@Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
	private String id;

	@Column(length = 20)
	private String parentId;

	@Column(length = 20)
	private String name;

	@Column(length = 200)
	private String summary;

	@Column(length = 20)
	private String userId;

	public String getId() {
		return id;
	}

	public Category setId(String id) {
		this.id = id;
		return this;
	}

	public String getParentId() {
		return parentId;
	}

	public Category setParentId(String parentId) {
		this.parentId = parentId;
		return this;
	}

	public String getName() {
		return name;
	}

	public Category setName(String name) {
		this.name = name;
		return this;
	}

	public String getSummary() {
		return summary;
	}

	public Category setSummary(String summary) {
		this.summary = summary;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public Category setUserId(String userId) {
		this.userId = userId;
		return this;
	}
}