package com.madao.model.entity.base;

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
@Table(name = "ba_dict",
		uniqueConstraints = @UniqueConstraint(columnNames = {"code"}),
		indexes = {
				@Index(name = "dict_code", columnList = "code"),
				@Index(name = "dict_parent_id", columnList = "parentId"),
				@Index(name = "dict_create_at", columnList = "createAt")
		})
public class Dict extends BasePojo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
	@Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
	private String id;
	@Column(length = 20)
	private String parentId;
	@Column(length = 10)
	private String code;
	@Column(length = 50)
	private String name;
	@Column(length = 200)
	private String description;
	@Column(length = 10)
	private String type;

}