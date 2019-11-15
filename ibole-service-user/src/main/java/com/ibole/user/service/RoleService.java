package com.ibole.user.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.user.Resource;
import com.ibole.pojo.user.Role;
import com.ibole.pojo.user.RoleResource;
import com.ibole.pojo.user.User;
import com.ibole.user.dao.RoleDao;
import com.ibole.user.dao.RoleResourceDao;
import com.ibole.utils.DateUtil;
import com.ibole.utils.IdGenerate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *  角色接口实现
 **/
@Service
public class RoleService {

	private final RoleDao roleDao;
	private final RoleResourceDao roleResourceDao;
	private final IdGenerate idGenerate;

	@Autowired
	public RoleService(RoleDao roleDao, RoleResourceDao roleResourceDao, IdGenerate idGenerate) {
		this.roleDao = roleDao;
		this.roleResourceDao = roleResourceDao;
		this.idGenerate = idGenerate;
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

	public Role findRoleById(String roleId) {
		Role role = roleDao.selectById(roleId);
		role.setResource(roleResourceDao.findResourcesOfRole(roleId));
		return role;
	}

	/**
	 * 更新角色、关联的资源
	 * @param role 角色实体
	 * @return boolean
	 */
	public boolean updateByPrimaryKey(Role role) {
		int i = roleDao.updateById(role);

		LambdaQueryWrapper<RoleResource> deleteWrapper = new LambdaQueryWrapper<>();
		deleteWrapper.eq(RoleResource::getUsRoleId,role.getId());
		roleResourceDao.delete(deleteWrapper);
		List<RoleResource> roleResources = new ArrayList<>();
		for (Resource resource : role.getResource()) {
			RoleResource roleResource = new RoleResource();
			roleResource.setId(String.valueOf(idGenerate.nextId()));
			roleResource.setUsResourceId(resource.getId());
			roleResource.setUsRoleId(role.getId());
			roleResources.add(roleResource);
		}
		roleResourceDao.insertBatch(roleResources);

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


	/**
	 * 查询当前角色的用户列表
	 * @param role 角色
	 * @return List<User>
	 */
	public List<User> findUsersOfRole(Role role) {
		return roleDao.findUsersOfRole(role.getId());
	}
}
