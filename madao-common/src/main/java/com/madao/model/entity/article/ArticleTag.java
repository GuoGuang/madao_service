package com.madao.model.entity.article;

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
@Table(name = "ar_article_tag",
        indexes = {
                @Index(name = "article_id", columnList = "article_id"),
                @Index(name = "tag_id", columnList = "tag_id")})
public class ArticleTag implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
    private String id;

    @Column(name = "article_id", nullable = false, length = 20)
    private String articleId;

    @Column(name = "tag_id", nullable = false, length = 20)
    private String tagId;

    public ArticleTag(String articleId, String tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }
    public ArticleTag() {
    }

	public String getId() {
		return id;
	}

	public ArticleTag setId(String id) {
		this.id = id;
		return this;
	}

	public String getArticleId() {
		return articleId;
	}

	public ArticleTag setArticleId(String articleId) {
		this.articleId = articleId;
		return this;
	}

	public String getTagId() {
		return tagId;
	}

	public ArticleTag setTagId(String tagId) {
		this.tagId = tagId;
		return this;
	}
}