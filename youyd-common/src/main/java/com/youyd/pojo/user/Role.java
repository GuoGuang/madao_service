package com.youyd.pojo.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description: 角色实体
 * @author: LGG
 * @create: 2018-09-27
 **/

@Getter
@Setter
public class Role extends BasePojo implements Serializable {

	@TableId(type = IdType.ID_WORKER_STR)
    private String id; // 角色表主键

    private String parentRoleId; // 父级角色id

    private String roleName; // 角色名称

    private String roleDesc; // 角色描述

    private String roleCode; // 角色编码

}