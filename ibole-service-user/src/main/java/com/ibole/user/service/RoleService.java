package com.ibole.user.service;


import com.ibole.exception.custom.ResourceNotFoundException;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.user.Resource;
import com.ibole.pojo.user.Role;
import com.ibole.pojo.user.RoleResource;
import com.ibole.pojo.user.User;
import com.ibole.user.dao.ResourceDao;
import com.ibole.user.dao.RoleDao;
import com.ibole.user.dao.RoleResourceDao;
import com.ibole.user.dao.UserDao;
import com.ibole.utils.DateUtil;
import com.ibole.utils.IdGenerate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色接口实现
 **/
@Service
public class RoleService {

	private final RoleDao roleDao;
	private final UserDao userDao;
	private final RoleResourceDao roleResourceDao;
	private final ResourceDao resourceDao;
	private final IdGenerate idGenerate;

	@Autowired
	public RoleService(RoleDao roleDao, RoleResourceDao roleResourceDao,
	                   IdGenerate idGenerate, ResourceDao resourceDao,
						UserDao userDao) {
		this.roleDao = roleDao;
		this.userDao = userDao;
		this.roleResourceDao = roleResourceDao;
		this.idGenerate = idGenerate;
		this.resourceDao = resourceDao;
	}


	/**
	 * 条件查询角色
	 * @param role : Role
	 * @return IPage<Role>
	 */
	public Page<Role> findRuleByCondition(Role role, QueryVO queryVO) {
		Specification<Role> condition = (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(role.getRoleName())) {
				predicates.add(builder.like(root.get("roleName"), "%" + role.getRoleName() + "%"));
			}
			Predicate[] ps = new Predicate[predicates.size()];
			query.where(builder.and(predicates.toArray(ps)));
			query.orderBy(builder.desc(root.get("createAt").as(Long.class)));
			return null;
		};
		return roleDao.findAll(condition, queryVO.getPageable());
	}

	public Role findRoleById(String roleId) {
		Role role = roleDao.findById(roleId).orElseThrow(ResourceNotFoundException::new);
		List<Resource> resourcesOfRole = resourceDao.findResourcesOfRole(roleId);
		role.setResource(resourcesOfRole);
		return role;
	}

	/**
	 * 更新角色、关联的资源
	 * @param role 角色实体
	 */
	public void saveOrUpdate(Role role) {
		if (StringUtils.isEmpty(role.getId())) {
			role.setCreateAt(DateUtil.getTimestamp());
			role.setUpdateAt(DateUtil.getTimestamp());
			roleDao.save(role);
		} else {
			roleDao.save(role);
			roleResourceDao.deleteByUsRoleId(role.getId());
		}
		List<RoleResource> roleResources = new ArrayList<>();
		for (Resource resource : role.getResource()) {
			RoleResource roleResource = new RoleResource();
			roleResource.setId(String.valueOf(idGenerate.nextId()));
			roleResource.setUsResourceId(resource.getId());
			roleResource.setUsRoleId(role.getId());
			roleResources.add(roleResource);
		}
		roleResourceDao.saveAll(roleResources);
	}

	public void deleteByIds(List<String> roleId) {
		roleDao.deleteBatch(roleId);
	}

	/**
	 * 查询当前角色的用户列表
	 *
	 * @param role 角色
	 * @return List<User>
	 */
	public List<User> findUsersOfRole(Role role) {
		List<User> usersOfRole = userDao.findUsersOfRole(role.getId());
		return usersOfRole;


	}
}
