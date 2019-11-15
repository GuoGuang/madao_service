package com.ibole.user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ibole.pojo.user.Resource;
import com.ibole.pojo.user.Role;
import com.ibole.pojo.user.User;

import java.util.List;

/**
 * 用户管理
 **/

public interface UserDao extends BaseMapper<User> {


	/**
	 * 查询当前用户的角色
	 * @param id 用户id
	 * @return 角色数组
	 */
	List<Role> findRolesOfUser(String id);

	/**
	 * 查询当前用户的资源列表
	 * @param id 用户id
	 * @return 角色数组
	 */
	List<Resource> findResourcesOfUser(String userId);
}
