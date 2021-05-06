package com.madao.model.pojo.article;

import com.madao.model.BasePojo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ar_comment",
        indexes = {
                @Index(name = "comments_article_id", columnList = "articleId"),
                @Index(name = "comments_parent_id", columnList = "parentId")
        })
public class Comment extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
    @Column(name = "id", columnDefinition = "char")
    private String id;

    /**
     * 评论人ID，未接入用户系统，暂时使用用户qq号作为唯一id
     */
    @Column(length = 20)
    private String userId;

    /**
     * 父评论ID(如果为0表示文章的顶级评论,每一条评论都可以被评论)
     */
    @Column(length = 20)
    private String parentId;

    @Column(length = 20)
    private String articleId;

    @Column(length = 200)
    private String content;

    @Column(length = 200)
    private Integer upvote;

    @Column(length = 200)
    private String avatar;

    @Column(length = 300)
    private String userName;

    @Column(length = 200)
    private String toId;

    @Column(length = 200)
    private String toName;

    @Column(length = 300)
    private String toAvatar;

	public String getId() {
		return id;
	}

	public Comment setId(String id) {
		this.id = id;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public Comment setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getParentId() {
		return parentId;
	}

	public Comment setParentId(String parentId) {
		this.parentId = parentId;
		return this;
	}

	public String getArticleId() {
		return articleId;
	}

	public Comment setArticleId(String articleId) {
		this.articleId = articleId;
		return this;
	}

	public String getContent() {
		return content;
	}

	public Comment setContent(String content) {
		this.content = content;
		return this;
	}

	public Integer getUpvote() {
		return upvote;
	}

	public Comment setUpvote(Integer upvote) {
		this.upvote = upvote;
		return this;
	}

	public String getAvatar() {
		return avatar;
	}

	public Comment setAvatar(String avatar) {
		this.avatar = avatar;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public Comment setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getToId() {
		return toId;
	}

	public Comment setToId(String toId) {
		this.toId = toId;
		return this;
	}

	public String getToName() {
		return toName;
	}

	public Comment setToName(String toName) {
		this.toName = toName;
		return this;
	}

	public String getToAvatar() {
		return toAvatar;
	}

	public Comment setToAvatar(String toAvatar) {
		this.toAvatar = toAvatar;
		return this;
	}
}