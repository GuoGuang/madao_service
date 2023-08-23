package com.madao.model.entity.article;

import com.madao.enums.ArticleAuditStatus;
import com.madao.enums.ArticleOriginType;
import com.madao.model.BasePojo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Getter
@Setter
@Entity
@Table(name = "ar_article",
		uniqueConstraints = @UniqueConstraint(columnNames = {"title"}),
		indexes = {
				@Index(name = "article_keywords", columnList = "keywords"),
				@Index(name = "article_title", columnList = "title"),
				@Index(name = "article_create_at", columnList = "createAt")})
@org.hibernate.annotations.Table(appliesTo = "ar_article", comment = "文章")
public class Article extends BasePojo implements Serializable {

	@Id
	@Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
	private String id;

	@Column(columnDefinition = "varchar(30) COMMENT '用户id' default ''", nullable = false)
	private String userId;

	@Column(length = 20)
	private String categoryId;

	@Column(length = 50)
	private String title;

	@Column(length = 200)
	private String thumb;

	@Column(length = 1, columnDefinition = "bit(1) COMMENT '是否公开'", nullable = false)
	private Boolean isPublic;

	@Column(length = 1, columnDefinition = "bit(1) COMMENT '是否推荐'", nullable = false)
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
	@Column(precision = 2, length = 5)
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
}