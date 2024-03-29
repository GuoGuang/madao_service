package com.madao.model.entity.user;

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
@Table(name = "us_role_resource",
		indexes = {
				@Index(name = "role_id", columnList = "role_id"),
				@Index(name = "resource_id", columnList = "resource_id")})
public class RoleResource implements Serializable {

	@Id
	@Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
	private String id;

	@Column(name = "role_id", nullable = false, length = 20)
	private String roleId;

	@Column(name = "resource_id", nullable = false, length = 20)
	private String resourceId;

	public RoleResource(String roleId, String resourceId) {
		this.resourceId = resourceId;
		this.roleId = roleId;
	}

}