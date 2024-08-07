package com.madao.user.service;

import com.madao.exception.custom.ResourceNotFoundException;
import com.madao.model.QueryVO;
import com.madao.model.dto.user.ResourceDto;
import com.madao.model.entity.user.Resource;
import com.madao.model.entity.user.RoleResource;
import com.madao.user.mapper.ResourceMapper;
import com.madao.user.repository.ResourceRepository;
import com.madao.user.repository.RoleResourceRepository;
import com.madao.utils.RedisUtil;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Service
@AllArgsConstructor
public class ResourceService {

	private final ResourceRepository resourceRepository;
	private final ResourceMapper resourceMapper;
	private final RoleResourceRepository roleResourceRepository;
	private final RedisUtil redisUtil;

	/**
	 * 条件查询资源
	 *
	 * @param resourceDto 资源实体
	 * @param queryVO     查询参数
	 * @return List
	 */
	public List<ResourceDto> findResourceByCondition(ResourceDto resourceDto, QueryVO queryVO) {

		Specification<Resource> condition = (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(resourceDto.getName())) {
				predicates.add(builder.like(root.get("name"), "%" + resourceDto.getName() + "%"));
			}
			Predicate[] ps = new Predicate[predicates.size()];
			return query.where(builder.and(predicates.toArray(ps))).getRestriction();
		};
		List<Resource> result = resourceRepository.findAll(condition);
		return resourceMapper.toDto(result);
	}

	public ResourceDto findResourceById(String resId) {
		return resourceRepository.findById(resId)
				.map(resourceMapper::toDto)
				.orElseThrow(ResourceNotFoundException::new);
	}

	public Set<ResourceDto> findResourceByRoleIds(List<String> resId) {
		return Optional.ofNullable(resourceRepository.findResourceByRoleIds(resId))
				.map(resourceMapper::toDto)
				.orElseThrow(ResourceNotFoundException::new);
	}

	public void saveOrUpdate(ResourceDto resourceDto) {
		resourceRepository.save(resourceMapper.toEntity(resourceDto));

		List<RoleResource> roleResources = resourceDto.getRoles().stream()
				.map(resource -> new RoleResource(resource.getId(), resourceDto.getId()))
				.toList();

		roleResourceRepository.deleteByRoleIdIn(Collections.singletonList(resourceDto.getId()));
		roleResourceRepository.saveAll(roleResources);
		redisUtil.del("resources");
	}

	public void deleteByIds(List<String> resId) {
		resourceRepository.deleteBatch(resId);
		roleResourceRepository.deleteByResourceIdIn(resId);
		redisUtil.del("resources");
	}
}
