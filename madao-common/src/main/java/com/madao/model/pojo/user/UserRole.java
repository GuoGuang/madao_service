package com.madao.model.pojo.user;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

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

	public String getId() {
		return id;
	}

	public UserRole setId(String id) {
		this.id = id;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public UserRole setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getRoleId() {
		return roleId;
	}

	public UserRole setRoleId(String roleId) {
		this.roleId = roleId;
		return this;
	}
}