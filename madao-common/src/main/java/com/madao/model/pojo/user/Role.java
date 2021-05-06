package com.madao.model.pojo.user;

import com.madao.model.BasePojo;
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
@Entity
@Table(name = "us_role",
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

	public String getId() {
		return id;
	}

	public Role setId(String id) {
		this.id = id;
		return this;
	}

	public String getRoleName() {
		return roleName;
	}

	public Role setRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public Role setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
		return this;
	}

	public String getCode() {
		return code;
	}

	public Role setCode(String code) {
		this.code = code;
		return this;
	}
}