package com.youyd.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youyd.pojo.user.Role;

import java.util.List;

/**
 * @description: 角色管理
 * @author: LGG
 * @create: 2018-09-26 16:21
 **/

public interface RoleDao extends BaseMapper<Role> {


	void updateRole(List roleId);
}
