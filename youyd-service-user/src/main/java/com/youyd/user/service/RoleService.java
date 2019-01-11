package com.youyd.user.service;

import com.youyd.user.pojo.Role;

import java.util.List;
import java.util.Map;

/**
 * @description: 角色管理接口
 * @author: LGG
 * @create: 2018-12-23
 **/
public interface RoleService {

	/**
	 * 更新角色状态
	 * @param roleId 角色id
	 */
	void updateRoleState(List roleId);

	/**
	 * 条件查询角色
	 * @param paramMap
	 * @return
	 */
	List findRuleByCondition(Map paramMap);

	boolean updateByPrimaryKey(Role role);

	boolean deleteByIds(List roleId);

	boolean insertSelective(Role role);

	Role findRuleById(String roleId);
}
