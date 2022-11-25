package com.madao.model.entity.user;

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
@Table(name = "us_role",
		uniqueConstraints = @UniqueConstraint(columnNames = {"code"}),
		indexes = {
				@Index(name = "role_code", columnList = "code"),
				@Index(name = "role_create_at", columnList = "createAt")
		})
public class Role extends BasePojo implements Serializable {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
	@Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
	private String id;

	@Column(length = 20)
	private String roleName;

	@Column(length = 200)
	private String roleDesc;

	@Column(length = 20)
	private String code;

}