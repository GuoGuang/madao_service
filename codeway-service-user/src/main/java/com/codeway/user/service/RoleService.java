package com.codeway.user.service;


import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.model.QueryVO;
import com.codeway.model.dto.user.RoleDto;
import com.codeway.model.pojo.user.QRole;
import com.codeway.model.pojo.user.Role;
import com.codeway.model.pojo.user.RoleResource;
import com.codeway.user.dao.ResourceDao;
import com.codeway.user.dao.RoleDao;
import com.codeway.user.dao.RoleResourceDao;
import com.codeway.user.dao.UserRoleDao;
import com.codeway.user.mapper.ResourceMapper;
import com.codeway.user.mapper.RoleMapper;
import com.codeway.utils.BeanUtil;
import com.codeway.utils.QuerydslUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色接口实现
 **/
@Service
public class RoleService {

	private final RoleDao roleDao;
	private final RoleResourceDao roleResourceDao;
	private final UserRoleDao userRoleDao;
	private final RoleMapper roleMapper;
	private final JPAQueryFactory jpaQueryFactory;

	private final ResourceDao resourceDao;
	private final ResourceMapper resourceMapper;

	public RoleService(RoleDao roleDao,
	                   RoleResourceDao roleResourceDao,
	                   UserRoleDao userRoleDao,
	                   RoleMapper roleMapper,
	                   JPAQueryFactory jpaQueryFactory, ResourceDao resourceDao, ResourceMapper resourceMapper) {
		this.roleDao = roleDao;
		this.roleResourceDao = roleResourceDao;
		this.userRoleDao = userRoleDao;
		this.roleMapper = roleMapper;
		this.jpaQueryFactory = jpaQueryFactory;
		this.resourceDao = resourceDao;
		this.resourceMapper = resourceMapper;
	}


	/**
	 * 条件查询角色
	 * @param roleDto : Role
	 * @return IPage<Role>
	 */
	public QueryResults<Role> findRuleByCondition(RoleDto roleDto, QueryVO queryVO) {

		QRole qRole = QRole.role;
		com.querydsl.core.types.Predicate predicate = null;
		OrderSpecifier<?> sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qRole);
		if (StringUtils.isNotEmpty(roleDto.getRoleName())) {
			predicate = ExpressionUtils.and(predicate, qRole.roleName.like(roleDto.getRoleName()));
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

	public List<RoleDto> getUseRoles(String id) {
		List<RoleDto> rolesOfUser = roleDao.findRolesOfUser(id)
				.map(roleMapper::toDto)
				.orElseThrow(ResourceNotFoundException::new);
		return rolesOfUser;
	}

	public RoleDto findRoleById(String roleId) {
		return roleDao.findById(roleId).map(role -> {
			RoleDto roleDto = roleMapper.toDto(role);
			roleDto.setResources(resourceMapper.toDto(
					resourceDao.findResourceByRoleIds(Collections.singletonList(role.getId()))
			));
			return roleDto;
		}).orElseThrow(ResourceNotFoundException::new);
	}


	/**
	 * 更新角色、关联的资源
	 *
	 * @param roleDto 角色实体
	 */
	public void saveOrUpdate(RoleDto roleDto) {

		if (StringUtils.isNotBlank(roleDto.getId())) {
			Role sourceRole = roleDao.findById(roleDto.getId()).orElseThrow(ResourceNotFoundException::new);
			BeanUtil.copyProperties(sourceRole, roleDto);
		}
		roleDao.save(roleMapper.toEntity(roleDto));

		List<RoleResource> roleResources = roleDto.getResources().stream()
				.map(role -> new RoleResource(roleDto.getId(), role.getId()))
				.collect(Collectors.toList());
		roleResourceDao.deleteByRoleIdIn(Collections.singletonList(roleDto.getId()));
		roleResourceDao.saveAll(roleResources);
	}

	public void deleteByIds(List<String> roleId) {
		roleDao.deleteBatch(roleId);
		userRoleDao.deleteByRoleIdIn(roleId);
		roleResourceDao.deleteByRoleIdIn(roleId);
	}

}
