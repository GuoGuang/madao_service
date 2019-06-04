package com.youyd.pojo.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户_角色中间表
 * @author : LGG
 * @create : 2019-06-04
 **/
@ApiModel(value="UserRole", description="用户_角色中间表")
@Getter
@Setter
public class UserRole {

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	private String usUserId; // 用户表id
	private String usRoleId; // 角色表id
}
