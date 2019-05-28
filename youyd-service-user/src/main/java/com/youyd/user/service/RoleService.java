package com.youyd.user.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.user.Role;
import com.youyd.user.dao.RoleDao;
import com.youyd.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  角色接口实现
 * @author : LGG
 * @create : 2018-09-27
 **/
@Service
public class RoleService {

	private final RoleDao roleDao;

	@Autowired
	public RoleService(RoleDao roleDao) {
		this.roleDao = roleDao;
	}


	/**
	 * 条件查询角色
	 * @param role : Role
	 * @return IPage<Role>
	 */
	public IPage<Role> findRuleByCondition(Role role, QueryVO queryVO ) {
		Page<Role> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());
		LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
		if (StringUtils.isNotEmpty(role.getRoleName())){
			queryWrapper.eq(Role::getRoleName,role.getRoleName());
		}
		queryWrapper.orderByDesc(Role::getCreateAt);
		return roleDao.selectPage(pr, queryWrapper);
	}

	/**
	 * 更新角色状态
	 * @param roleId 角色id
	 */
	public void updateRoleState(List roleId) {
		roleDao.updateRole(roleId);
	}

	public boolean updateByPrimaryKey(Role role) {
		int i = roleDao.updateById(role);
		return SqlHelper.retBool(i);
	}

	public boolean deleteByIds(List<String> roleId) {
		int i = roleDao.deleteBatchIds(roleId);
		return SqlHelper.retBool(i);
	}

	public boolean insertSelective(Role role) {
		role.setCreateAt(DateUtil.getTimestamp());
		role.setUpdateAt(DateUtil.getTimestamp());
		int insert = roleDao.insert(role);
		return SqlHelper.retBool(insert);
	}

	public Role findRuleById(String roleId) {
		return roleDao.selectById(roleId);
	}
}
