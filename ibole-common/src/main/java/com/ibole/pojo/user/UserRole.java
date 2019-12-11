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
 * 用户_角色中间表
 **/
@ApiModel(value = "UserRole", description = "用户_角色中间表")
@Getter
@Setter
@Entity
@Table(name = "us_user_role")
public class UserRole {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.ibole.config.IdGeneratorConfig")
    private String id;
    private String usUserId; // 用户表id
    private String usRoleId; // 角色表id
}
