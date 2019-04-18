package com.youyd.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.pojo.base.Role;
import com.youyd.user.dao.RoleDao;
import com.youyd.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @description: 角色接口实现
 * @author: LGG
 * @create: 2018-09-27
 **/
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	/**
	 * 更新角色状态
	 * @param roleId 角色id
	 */
	@Override
	public void updateRoleState(List roleId) {
		roleDao.updateRole(roleId);
	}

	/**
	 * 条件查询角色
	 * @param paramMap
	 * @return
	 */
	@Override
	public List findRuleByCondition(Map paramMap) {
		QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
		return roleDao.selectList(queryWrapper);
	}

	@Override
	public boolean updateByPrimaryKey(Role role) {
		int i = roleDao.updateById(role);
		return SqlHelper.retBool(i);
	}

	@Override
	public boolean deleteByIds(List roleId) {
		int i = roleDao.deleteBatchIds(roleId);
		return SqlHelper.retBool(i);
	}

	@Override
	public boolean insertSelective(Role role) {
		int insert = roleDao.insert(role);
		return SqlHelper.retBool(insert);
	}

	@Override
	public Role findRuleById(String roleId) {
		return roleDao.selectById(roleId);
	}
}
