package com.madao.model.dto.article;

import com.google.common.base.Objects;
import com.madao.model.BasePojo;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class CommentDto extends BasePojo implements Serializable {

	@Schema(title = "文章的评论表主键")
	private String id;

	@NotNull(message = "ID不能为空")
	@Schema(title = "评论人ID，未接入用户系统，暂时使用用户qq号作为唯一id")
	private String userId;

	@Schema(title = "父评论ID(如果为0表示文章的顶级评论,每一条评论都可以被评论)")
	private String parentId;

	@NotNull(message = "文章ID不能为空")
	@Schema(title = "外键文章表ID")
	private String articleId;

	@NotNull(message = "评论内容不能为空")
	@Schema(title = "评论内容")
	private String content;

	@Schema(title = "点赞量")
	private Integer upvote;

	@NotNull(message = "头像不能为空")
	@Schema(title = "头像")
	private String avatar;

	@Schema(title = "评论人名称")
	private String userName;

	@NotNull(message = "被评论人不能为空")
	@Schema(title = "被评论人id")
	private String toId;

	@Schema(title = "头像")
	private String toName;

	@Schema(title = "被评论人头像")
	private String toAvatar;

	@Schema(title = "回复")
	private List<CommentDto> reply;

	public String getId() {
		return id;
	}

	public CommentDto setId(String id) {
		this.id = id;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public CommentDto setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getParentId() {
		return parentId;
	}

	public CommentDto setParentId(String parentId) {
		this.parentId = parentId;
		return this;
	}

	public String getArticleId() {
		return articleId;
	}

	public CommentDto setArticleId(String articleId) {
		this.articleId = articleId;
		return this;
	}

	public String getContent() {
		return content;
	}

	public CommentDto setContent(String content) {
		this.content = content;
		return this;
	}

	public Integer getUpvote() {
		return upvote;
	}

	public CommentDto setUpvote(Integer upvote) {
		this.upvote = upvote;
		return this;
	}

	public String getAvatar() {
		return avatar;
	}

	public CommentDto setAvatar(String avatar) {
		this.avatar = avatar;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public CommentDto setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getToId() {
		return toId;
	}

	public CommentDto setToId(String toId) {
		this.toId = toId;
		return this;
	}

	public String getToName() {
		return toName;
	}

	public CommentDto setToName(String toName) {
		this.toName = toName;
		return this;
	}

	public String getToAvatar() {
		return toAvatar;
	}

	public CommentDto setToAvatar(String toAvatar) {
		this.toAvatar = toAvatar;
		return this;
	}

	public List<CommentDto> getReply() {
		return reply;
	}

	public CommentDto setReply(List<CommentDto> reply) {
		this.reply = reply;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CommentDto that)) return false;
		if (!super.equals(o)) return false;
		return Objects.equal(id, that.id) && Objects.equal(userId, that.userId) && Objects.equal(parentId, that.parentId) && Objects.equal(articleId, that.articleId) && Objects.equal(content, that.content) && Objects.equal(upvote, that.upvote) && Objects.equal(avatar, that.avatar) && Objects.equal(userName, that.userName) && Objects.equal(toId, that.toId) && Objects.equal(toName, that.toName) && Objects.equal(toAvatar, that.toAvatar) && Objects.equal(reply, that.reply);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), id, userId, parentId, articleId, content, upvote, avatar, userName, toId, toName, toAvatar, reply);
	}
}