package com.madaoo.model.pojo.article;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "ar_article_tag",
		indexes = {
				@Index(name = "article_id", columnList = "article_id"),
				@Index(name = "tag_id", columnList = "tag_id")})
public class ArticleTag implements Serializable {

	@Id
	@Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.madaoo.config.IdGeneratorConfig")
	private String id;

	@Column(name = "article_id", nullable = false, length = 20)
	private String articleId;

	@Column(name = "tag_id", nullable = false, length = 20)
	private String tagId;

	public ArticleTag(String articleId, String tagId) {
		this.articleId = articleId;
		this.tagId = tagId;
	}
}