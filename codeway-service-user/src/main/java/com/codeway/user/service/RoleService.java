package com.codeway.user.service;


import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.pojo.QueryVO;
import com.codeway.pojo.user.QRole;
import com.codeway.pojo.user.Resource;
import com.codeway.pojo.user.Role;
import com.codeway.pojo.user.User;
import com.codeway.user.dao.ResourceDao;
import com.codeway.user.dao.RoleDao;
import com.codeway.user.dao.UserDao;
import com.codeway.utils.BeanUtil;
import com.codeway.utils.IdGenerate;
import com.codeway.utils.QuerydslUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色接口实现
 **/
@Service
public class RoleService {

    private final RoleDao roleDao;
    private final UserDao userDao;
    private final ResourceDao resourceDao;
    private final IdGenerate idGenerate;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Autowired
    public RoleService(RoleDao roleDao,
                       IdGenerate idGenerate, ResourceDao resourceDao,
                       UserDao userDao) {
        this.roleDao = roleDao;
        this.userDao = userDao;
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
		return role;
	}


	/**
	 * 更新角色、关联的资源
	 * @param role 角色实体
	 */
	public void saveOrUpdate(Role role) {
		List<String> ids = role.getResources().stream()
				.map(Resource::getId)
				.collect(Collectors.toList());
		List<Resource> resourceList = resourceDao.findAllById(ids);
		Set<Resource> resourceSet = new HashSet<>(resourceList);
		role.setResources(resourceSet);
		if (StringUtils.isNotBlank(role.getId())){
			Role sourceRole = roleDao.findById(role.getId()).orElseThrow(ResourceNotFoundException::new);
			BeanUtil.copyProperties(sourceRole, role);
		}
		roleDao.save(role);

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
