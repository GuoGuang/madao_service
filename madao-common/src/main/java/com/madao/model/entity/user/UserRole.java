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
@Table(name = "us_user_role",
		indexes = {
				@Index(name = "user_id", columnList = "user_id"),
				@Index(name = "role_id", columnList = "role_id")})
public class UserRole implements Serializable {

	@Id
	@Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.madao.config.IdGeneratorConfig")
	private String id;

	@Column(name = "user_id", nullable = false, length = 20)
	private String userId;

	@Column(name = "role_id", nullable = false, length = 20)
	private String roleId;

	public UserRole(String userId, String roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	public UserRole() {
	}
}