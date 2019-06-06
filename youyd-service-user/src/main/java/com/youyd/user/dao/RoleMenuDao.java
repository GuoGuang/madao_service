package com.youyd.user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youyd.pojo.user.Menu;
import com.youyd.pojo.user.RoleMenu;

import java.util.List;

/**
 * 角色_菜单管理
 * @author : LGG
 * @create : 2019-06-04
 **/

public interface RoleMenuDao extends BaseMapper<RoleMenu> {


	/**
	 * 根据此角色id查询匹配的菜单列表
	 * @param roleId 角色id
	 * @return
	 */
	List<Menu> findMenusOfRole(String roleId);

	void insertBatch(List<RoleMenu> menus);
}
