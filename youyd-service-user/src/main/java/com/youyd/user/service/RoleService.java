package com.youyd.user.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.user.Menu;
import com.youyd.pojo.user.Role;
import com.youyd.pojo.user.RoleMenu;
import com.youyd.user.dao.RoleDao;
import com.youyd.user.dao.RoleMenuDao;
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
	private final RoleMenuDao roleMenuDao;

	@Autowired
	public RoleService(RoleDao roleDao, RoleMenuDao roleMenuDao) {
		this.roleDao = roleDao;
		this.roleMenuDao = roleMenuDao;
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
		role.setMenus(roleMenuDao.findMenusOfRole(roleId));
		return role;
	}

	/**
	 * 更新角色状态
	 * @param roleId 角色id
	 */
	public void updateRoleState(List roleId) {
		roleDao.updateRole(roleId);
	}

	/**
	 * 更新角色、关联的菜单
	 * @param role 角色实体
	 * @return boolean
	 */
	public boolean updateByPrimaryKey(Role role) {
		int i = roleDao.updateById(role);

		LambdaQueryWrapper<RoleMenu> deleteWrapper = new LambdaQueryWrapper<>();
		deleteWrapper.eq(RoleMenu::getUsRoleId,role.getId());
		roleMenuDao.delete(deleteWrapper);

		List<Menu> menus = role.getMenus();
		for (Menu menu : menus) {
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setUsMenuId(menu.getId());
			roleMenu.setUsRoleId(role.getId());
			roleMenuDao.insert(roleMenu);
		}

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

}
