package com.madao.model.dto.article;

import com.google.common.base.Objects;
import com.madao.model.BasePojo;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Schema(title = "ar_category", description = "文章分类")
public class CategoryDto extends BasePojo implements Serializable {

	@Schema(title = "文章分类表主键")
	private String id;

	@Schema(title = "父ID")
	private String parentId;

	@Schema(title = "分类名称")
	@NotNull(message = "分类名称不能为空")
	private String name;

	@Schema(title = "分类简介")
	@NotNull(message = "分类简介不能为空")
	private String summary;

	@Schema(title = "用户ID")
	private String userId;

	@Schema(title = "状态")
	private Integer state = 1;

	@Schema(title = "文章数量")
	private Integer articleCount;

	public String getId() {
		return id;
	}

	public CategoryDto setId(String id) {
		this.id = id;
		return this;
	}

	public String getParentId() {
		return parentId;
	}

	public CategoryDto setParentId(String parentId) {
		this.parentId = parentId;
		return this;
	}

	public String getName() {
		return name;
	}

	public CategoryDto setName(String name) {
		this.name = name;
		return this;
	}

	public String getSummary() {
		return summary;
	}

	public CategoryDto setSummary(String summary) {
		this.summary = summary;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public CategoryDto setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public Integer getState() {
		return state;
	}

	public CategoryDto setState(Integer state) {
		this.state = state;
		return this;
	}

	public Integer getArticleCount() {
		return articleCount;
	}

	public CategoryDto setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CategoryDto that)) return false;
		if (!super.equals(o)) return false;
		return Objects.equal(id, that.id) && Objects.equal(parentId, that.parentId) && Objects.equal(name, that.name) && Objects.equal(summary, that.summary) && Objects.equal(userId, that.userId) && Objects.equal(state, that.state) && Objects.equal(articleCount, that.articleCount);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), id, parentId, name, summary, userId, state, articleCount);
	}
}