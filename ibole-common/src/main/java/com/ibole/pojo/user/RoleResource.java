package com.ibole.pojo.user;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色_资源中间表
 **/
@ApiModel(value = "RoleResource", description = "角色_资源中间表")
@Getter
@Setter
@Entity
@Table(name = "us_role_resource")
public class RoleResource {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.ibole.config.IdGeneratorConfig")
    private String id;
    private String usRoleId; // 角色表_id
    private String usResourceId; // 资源表_id
}