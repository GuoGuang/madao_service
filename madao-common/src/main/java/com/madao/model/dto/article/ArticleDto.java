package com.madao.model.dto.article;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import com.madao.enums.ArticleAuditStatus;
import com.madao.enums.ArticleOriginType;
import com.madao.model.BasePojo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Schema(title = "article", description = "文章类")
public class ArticleDto extends BasePojo implements Serializable {

	@Schema(title = "推荐阅读", example = "1")
	@JsonIgnoreProperties("related")
	private List<ArticleDto> related;

	private List<TagDto> tags;

	@Schema(title = "分类id", example = "1")
	private String categoryId;

	@Schema(title = "标签id", example = "1")
	private String tagsId;

	@Schema(title = "用户名")
	private String userName;

	@Schema(title = "ID")
	private String id;

	@Schema(title = "用户ID")
	private String userId;

	@Schema(title = "标题")
	@NotNull(message = "标题不能为空")
	private String title;

	@Schema(title = "文章封面")
	private String thumb;

	@Schema(title = "是否公开")
	private Boolean isPublic;

	@Schema(title = "是否置顶")
	private Boolean isTop;

	@Schema(title = "浏览量")
	private Integer visits;

	@Schema(title = "点赞数")
	private Integer upvote;

	@Schema(title = "评论数")
	private Integer comment;

	@Schema(title = "审核状态", example = "1")
	private ArticleAuditStatus reviewState;

	@Schema(title = "URL")
	private String url;

	@Schema(title = "热度")
	private float importance;

	@Schema(title = "文章描述")
	@NotNull(message = "概述不能为空")
	private String description;

	@Schema(title = "关键字")
	private String keywords;

	@Schema(title = "来源")
	@NotNull(message = "来源不能为空")
	private ArticleOriginType origin;

	@Schema(title = "文章正文")
	@NotNull(message = "内容不能为空")
	private String content;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ArticleDto that)) return false;
		if (!super.equals(o)) return false;
		return Float.compare(that.importance, importance) == 0 && Objects.equal(related, that.related) && Objects.equal(tags, that.tags) && Objects.equal(categoryId, that.categoryId) && Objects.equal(tagsId, that.tagsId) && Objects.equal(userName, that.userName) && Objects.equal(id, that.id) && Objects.equal(userId, that.userId) && Objects.equal(title, that.title) && Objects.equal(thumb, that.thumb) && Objects.equal(isPublic, that.isPublic) && Objects.equal(isTop, that.isTop) && Objects.equal(visits, that.visits) && Objects.equal(upvote, that.upvote) && Objects.equal(comment, that.comment) && reviewState == that.reviewState && Objects.equal(url, that.url) && Objects.equal(description, that.description) && Objects.equal(keywords, that.keywords) && origin == that.origin && Objects.equal(content, that.content);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), related, tags, categoryId, tagsId, userName, id, userId, title, thumb, isPublic, isTop, visits, upvote, comment, reviewState, url, importance, description, keywords, origin, content);
	}
}