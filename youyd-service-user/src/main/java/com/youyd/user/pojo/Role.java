package com.youyd.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;

import java.io.Serializable;

/**
 * @description: 角色实体
 * @author: LGG
 * @create: 2018-09-27
 **/
public class Role extends BasePojo implements Serializable {

	@TableId(type = IdType.ID_WORKER)
    private Long roleId; // 角色表主键

    private String parentRoleId; // 父级角色id

    private String roleName; // 角色名称

    private String roleDesc; // 角色描述

    private String roleCode; // 角色编码

    private static final long serialVersionUID = 1L;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getParentRoleId() {
        return parentRoleId;
    }

    public void setParentRoleId(String parentRoleId) {
        this.parentRoleId = parentRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

}