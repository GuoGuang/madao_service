package com.ibole.user.service;


import com.ibole.exception.custom.ResourceNotFoundException;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.user.*;
import com.ibole.user.dao.ResourceDao;
import com.ibole.user.dao.RoleDao;
import com.ibole.user.dao.RoleResourceDao;
import com.ibole.user.dao.UserDao;
import com.ibole.utils.DateUtil;
import com.ibole.utils.IdGenerate;
import com.ibole.utils.QuerydslUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    JPAQueryFactory jpaQueryFactory;

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
     *
     * @param role : Role
     * @return IPage<Role>
     */
    public QueryResults<Role> findRuleByCondition(Role role, QueryVO queryVO) {

        QRole qRole = QRole.role;
        com.querydsl.core.types.Predicate predicate = null;
        OrderSpecifier<?> sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qRole);
        if (StringUtils.isNotEmpty(role.getRoleName())) {
            predicate = ExpressionUtils.and(predicate, qRole.roleName.like(role.getRoleName()));
        }
        if (StringUtils.isNotEmpty(queryVO.getFieldSort())) {
            sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qRole, queryVO.getFieldSort());
        }
        QueryResults<Role> queryResults = jpaQueryFactory
                .selectFrom(qRole)
                .where(predicate)
                .offset(queryVO.getPageNum())
                .limit(queryVO.getPageSize())
                .orderBy(sortedColumn)
                .fetchResults();
        return queryResults;
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
