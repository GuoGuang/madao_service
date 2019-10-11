package com.youyd.pojo.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @description 角色实体
 * @author LGG
 * @create 2018-09-27
 **/

@Getter
@Setter
public class Role extends BasePojo implements Serializable {

	@TableField(exist = false)
    private List<Resource> resource; // 角色关联的资源

	@TableId(type = IdType.ID_WORKER_STR)
    private String id; // 角色表主键

	@NotNull(message="角色名称不能为空")
    private String roleName; // 角色名称

    private String roleDesc; // 角色描述

	@NotNull(message="角色编码不能为空")
    private String roleCode; // 角色编码

}