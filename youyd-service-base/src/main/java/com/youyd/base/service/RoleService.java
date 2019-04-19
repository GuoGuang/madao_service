package com.youyd.base.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.base.dao.RoleDao;
import com.youyd.pojo.base.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 角色接口实现
 * @author: LGG
 * @create: 2018-09-27
 **/
@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;

	/**
	 * 更新角色状态
	 * @param roleId 角色id
	 */
	public void updateRoleState(List roleId) {
		roleDao.updateRole(roleId);
	}

	/**
	 * 条件查询角色
	 * @param paramMap
	 * @return
	 */
	public IPage<Role> findRuleByCondition(Role role) {
		Page<Role> pr = new Page<>(role.getPageNum(),role.getPageSize());
		LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
		if (StringUtils.isNotEmpty(role.getRoleName())){
			queryWrapper.eq(Role::getRoleName,role.getRoleName());
		}
		IPage<Role> roleIPage = roleDao.selectPage(pr, queryWrapper);
		return roleIPage;
	}

	public boolean updateByPrimaryKey(Role role) {
		int i = roleDao.updateById(role);
		return SqlHelper.retBool(i);
	}

	public boolean deleteByIds(List<Long> roleId) {
		int i = roleDao.deleteBatchIds(roleId);
		return SqlHelper.retBool(i);
	}

	public boolean insertSelective(Role role) {
		int insert = roleDao.insert(role);
		return SqlHelper.retBool(insert);
	}

	public Role findRuleById(String roleId) {
		return roleDao.selectById(roleId);
	}
}
