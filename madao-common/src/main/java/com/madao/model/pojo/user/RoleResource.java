package com.madao.model.pojo.user;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

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

	public String getId() {
		return id;
	}

	public RoleResource setId(String id) {
		this.id = id;
		return this;
	}

	public String getRoleId() {
		return roleId;
	}

	public RoleResource setRoleId(String roleId) {
		this.roleId = roleId;
		return this;
	}

	public String getResourceId() {
		return resourceId;
	}

	public RoleResource setResourceId(String resourceId) {
		this.resourceId = resourceId;
		return this;
	}
}