package com.madao.model.entity.article;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
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
}