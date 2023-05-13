package com.madao.model.entity.article;

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
@Table(name = "ar_tag",
		uniqueConstraints = @UniqueConstraint(columnNames = {"slug"}),
		indexes = {@Index(name = "tag_name", columnList = "name")
		})
public class Tag extends BasePojo implements Serializable {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
	@Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
	private String id;

	@Column(length = 20)
	private String name;

	/**
	 * 英文名称
	 */
	@Column(length = 20)
	private String slug;

	@Column(length = 200)
	private String description;

	@Column(length = 50)
	private String icon;

	@Column(length = 30)
	private String color;

	@Column(length = 1)
	private Integer state;

}