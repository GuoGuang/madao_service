package com.madao.model.entity.article;

import com.madao.enums.ArticleAuditStatus;
import com.madao.enums.ArticleOriginType;
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
@Table(name = "ar_article",
        indexes = {
                @Index(name = "article_keywords", columnList = "keywords"),
                @Index(name = "article_title", columnList = "title"),
                @Index(name = "article_create_at", columnList = "createAt")})
public class Article extends BasePojo implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
    private String id;

    @Column(length = 20)
    private String userId;

    @Column(length = 20)
    private String categoryId;

    @Column(length = 50)
    private String title;

    @Column(length = 200)
    private String thumb;

    @Column(length = 1)
    private Boolean isPublic;

    @Column(length = 1)
    private Boolean isTop;

    @Column(length = 5)
    private Integer visits;

    @Column(length = 5)
    private Integer upvote;

    @Column(length = 5)
    private Integer comment;

    @Column(length = 1)
    private ArticleAuditStatus reviewState;

    @Column(length = 200)
    private String url;

    /**
     * 热度
     */
    @Column(precision = 2, scale = 1, length = 5)
    private float importance;

    @Column(length = 500)
    private String description;

    @Column(length = 200)
    private String keywords;

    @Column(length = 1)
    private ArticleOriginType origin;

    @Lob
    @Column(columnDefinition = "text")
    private String content;

	public String getId() {
		return id;
	}

	public Article setId(String id) {
		this.id = id;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public Article setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public Article setCategoryId(String categoryId) {
		this.categoryId = categoryId;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Article setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getThumb() {
		return thumb;
	}

	public Article setThumb(String thumb) {
		this.thumb = thumb;
		return this;
	}

	public Boolean getPublic() {
		return isPublic;
	}

	public Article setPublic(Boolean aPublic) {
		isPublic = aPublic;
		return this;
	}

	public Boolean getTop() {
		return isTop;
	}

	public Article setTop(Boolean top) {
		isTop = top;
		return this;
	}

	public Integer getVisits() {
		return visits;
	}

	public Article setVisits(Integer visits) {
		this.visits = visits;
		return this;
	}

	public Integer getUpvote() {
		return upvote;
	}

	public Article setUpvote(Integer upvote) {
		this.upvote = upvote;
		return this;
	}

	public Integer getComment() {
		return comment;
	}

	public Article setComment(Integer comment) {
		this.comment = comment;
		return this;
	}

	public ArticleAuditStatus getReviewState() {
		return reviewState;
	}

	public Article setReviewState(ArticleAuditStatus reviewState) {
		this.reviewState = reviewState;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public Article setUrl(String url) {
		this.url = url;
		return this;
	}

	public float getImportance() {
		return importance;
	}

	public Article setImportance(float importance) {
		this.importance = importance;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Article setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getKeywords() {
		return keywords;
	}

	public Article setKeywords(String keywords) {
		this.keywords = keywords;
		return this;
	}

	public ArticleOriginType getOrigin() {
		return origin;
	}

	public Article setOrigin(ArticleOriginType origin) {
		this.origin = origin;
		return this;
	}

	public String getContent() {
		return content;
	}

	public Article setContent(String content) {
		this.content = content;
		return this;
	}
}