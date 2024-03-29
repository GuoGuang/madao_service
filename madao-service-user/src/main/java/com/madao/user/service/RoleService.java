package com.madao.user.service;

import com.madao.exception.custom.ResourceNotFoundException;
import com.madao.model.QueryVO;
import com.madao.model.dto.user.RoleDto;
import com.madao.model.entity.user.QRole;
import com.madao.model.entity.user.Role;
import com.madao.model.entity.user.RoleResource;
import com.madao.user.dao.ResourceDao;
import com.madao.user.dao.RoleDao;
import com.madao.user.dao.RoleResourceDao;
import com.madao.user.dao.UserRoleDao;
import com.madao.user.mapper.ResourceMapper;
import com.madao.user.mapper.RoleMapper;
import com.madao.utils.BeanUtil;
import com.madao.utils.QuerydslUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Service
@AllArgsConstructor
public class RoleService {

	private final RoleDao roleDao;
	private final RoleResourceDao roleResourceDao;
	private final UserRoleDao userRoleDao;
	private final RoleMapper roleMapper;
	private final JPAQueryFactory jpaQueryFactory;

	private final ResourceDao resourceDao;
	private final ResourceMapper resourceMapper;

	/**
	 * 条件查询角色
	 *
	 * @param roleDto : Role
	 * @return IPage<Role>
	 */
	public QueryResults<Role> findRuleByCondition(RoleDto roleDto, QueryVO queryVO) {

		QRole qRole = QRole.role;
		Predicate predicate = null;
		OrderSpecifier<?> sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qRole);
		if (StringUtils.isNotEmpty(roleDto.getRoleName())) {
			predicate = ExpressionUtils.and(predicate, qRole.roleName.like(roleDto.getRoleName()));
		}
		if (StringUtils.isNotEmpty(queryVO.getFieldSort())) {
			sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qRole, queryVO.getFieldSort());
		}
		return jpaQueryFactory
				.selectFrom(qRole)
				.where(predicate)
				.offset(queryVO.getPageNum())
				.limit(queryVO.getPageSize())
				.orderBy(sortedColumn)
				.fetchResults();
	}

	public List<RoleDto> getUseRoles(String id) {
		return roleDao.findRolesOfUser(id)
				.map(roleMapper::toDto)
				.orElseThrow(ResourceNotFoundException::new);
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
				.toList();
		roleResourceDao.deleteByRoleIdIn(Collections.singletonList(roleDto.getId()));
		roleResourceDao.saveAll(roleResources);
	}

	public void deleteByIds(List<String> roleId) {
		roleDao.deleteBatch(roleId);
		userRoleDao.deleteByRoleIdIn(roleId);
		roleResourceDao.deleteByRoleIdIn(roleId);
	}

}
