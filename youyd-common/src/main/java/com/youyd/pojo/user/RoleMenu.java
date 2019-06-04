package com.youyd.pojo.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色_菜单中间表
 * @author : LGG
 * @create : 2019-06-04
 **/
@ApiModel(value="RoleMenu", description="角色_菜单中间表")
@Getter
@Setter
public class RoleMenu {

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	private String usRoleId; // 角色表_id
	private String usMenuId; // 菜单表_id
}