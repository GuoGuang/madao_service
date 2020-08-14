package com.codeway.model.pojo.article;

import com.codeway.enums.ArticleAuditStatus;
import com.codeway.enums.ArticleOriginStatus;
import com.codeway.model.BasePojo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ar_article",
		indexes = {
				@Index(name = "article_keywords", columnList = "keywords"),
				@Index(name = "article_title", columnList = "title"),
				@Index(name = "article_create_at", columnList = "createAt")})
public class Article extends BasePojo implements Serializable {


	@JoinColumn(name = "category_id",foreignKey=@ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Category category;

	@Transient
	@JsonIgnoreProperties("related")
	private List<Article> related;

	@Transient
	private String categoryId;

	@Transient
	private String tagsId;

	@Transient
	private String userName;

	@JoinTable(
			name = "ar_article_tags",
			joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT)),
			inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT)))
	@ManyToMany
	private Set<Tag> tags = new HashSet<>();

	@Id
	@Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.codeway.config.IdGeneratorConfig")
	private String id;

	@Column(length = 20)
	private String userId;

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
	private ArticleOriginStatus origin;

	@Lob
	@Column(columnDefinition = "text")
	private String content;

}