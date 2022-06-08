package com.madao.model.dto.article;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import com.madao.enums.ArticleAuditStatus;
import com.madao.enums.ArticleOriginType;
import com.madao.model.BasePojo;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

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

	public List<ArticleDto> getRelated() {
		return related;
	}

	public ArticleDto setRelated(List<ArticleDto> related) {
		this.related = related;
		return this;
	}

	public List<TagDto> getTags() {
		return tags;
	}

	public ArticleDto setTags(List<TagDto> tags) {
		this.tags = tags;
		return this;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public ArticleDto setCategoryId(String categoryId) {
		this.categoryId = categoryId;
		return this;
	}

	public String getTagsId() {
		return tagsId;
	}

	public ArticleDto setTagsId(String tagsId) {
		this.tagsId = tagsId;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public ArticleDto setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getId() {
		return id;
	}

	public ArticleDto setId(String id) {
		this.id = id;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public ArticleDto setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public ArticleDto setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getThumb() {
		return thumb;
	}

	public ArticleDto setThumb(String thumb) {
		this.thumb = thumb;
		return this;
	}

	public Boolean getPublic() {
		return isPublic;
	}

	public ArticleDto setPublic(Boolean aPublic) {
		isPublic = aPublic;
		return this;
	}

	public Boolean getTop() {
		return isTop;
	}

	public ArticleDto setTop(Boolean top) {
		isTop = top;
		return this;
	}

	public Integer getVisits() {
		return visits;
	}

	public ArticleDto setVisits(Integer visits) {
		this.visits = visits;
		return this;
	}

	public Integer getUpvote() {
		return upvote;
	}

	public ArticleDto setUpvote(Integer upvote) {
		this.upvote = upvote;
		return this;
	}

	public Integer getComment() {
		return comment;
	}

	public ArticleDto setComment(Integer comment) {
		this.comment = comment;
		return this;
	}

	public ArticleAuditStatus getReviewState() {
		return reviewState;
	}

	public ArticleDto setReviewState(ArticleAuditStatus reviewState) {
		this.reviewState = reviewState;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public ArticleDto setUrl(String url) {
		this.url = url;
		return this;
	}

	public float getImportance() {
		return importance;
	}

	public ArticleDto setImportance(float importance) {
		this.importance = importance;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public ArticleDto setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getKeywords() {
		return keywords;
	}

	public ArticleDto setKeywords(String keywords) {
		this.keywords = keywords;
		return this;
	}

	public ArticleOriginType getOrigin() {
		return origin;
	}

	public ArticleDto setOrigin(ArticleOriginType origin) {
		this.origin = origin;
		return this;
	}

	public String getContent() {
		return content;
	}

	public ArticleDto setContent(String content) {
		this.content = content;
		return this;
	}

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