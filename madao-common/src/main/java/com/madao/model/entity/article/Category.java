package com.madao.model.entity.article;

import com.madao.model.BasePojo;
import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
@Entity
@Table(name = "ar_category",
		uniqueConstraints = @UniqueConstraint(columnNames = {"name"}),
		indexes = {
				@Index(name = "categories_name", columnList = "name"),
				@Index(name = "categories_parent_id", columnList = "parentId")
		})
public class Category extends BasePojo implements Serializable {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
	@Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
	private String id;

	@Column(length = 20)
	private String parentId;

	@Column(length = 20)
	private String name;

	@Column(length = 200)
	private String summary;

	@Column(length = 20)
	private String userId;

}