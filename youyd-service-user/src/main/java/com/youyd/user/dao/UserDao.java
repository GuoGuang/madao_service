package com.youyd.user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youyd.pojo.user.Menu;
import com.youyd.pojo.user.Role;
import com.youyd.pojo.user.User;

import java.util.List;

/**
 * 用户管理
 * @author : LGG
 * @create : 2018-09-26 16:21
 **/

public interface UserDao extends BaseMapper<User> {


	/**
	 * 查询当前用户的角色
	 * @param id 用户id
	 * @return 角色数组
	 */
	List<Role> findRolesOfUser(String id);

	/**
	 * 查询当前用户的菜单列表
	 * @param id 用户id
	 * @return 角色数组
	 */
	List<Menu> findMenusOfUser(String userId);
}
