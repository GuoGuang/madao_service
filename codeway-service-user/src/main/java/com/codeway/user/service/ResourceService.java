package com.codeway.user.service;


import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.pojo.QueryVO;
import com.codeway.pojo.user.Resource;
import com.codeway.user.dao.ResourceDao;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 资源接口实现
 **/
@Service
public class ResourceService {

    private final ResourceDao resourceDao;

	private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public ResourceService(ResourceDao resourceDao, JPAQueryFactory jpaQueryFactory) {
        this.resourceDao = resourceDao;
		this.jpaQueryFactory = jpaQueryFactory;
	}

    /**
     * 条件查询资源
     *
     * @param resource 资源实体
     * @param queryVO  查询参数
     * @return List
     */
	public List<Resource> findResourceByCondition(Resource resource, QueryVO queryVO) {

		Specification<Resource> condition = (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(resource.getName())) {
				predicates.add(builder.like(root.get("name"), "%" + resource.getName() + "%"));
			}
			Predicate[] ps = new Predicate[predicates.size()];
			return query.where(builder.and(predicates.toArray(ps))).getRestriction();
		};
		return resourceDao.findAll(condition);
	}

	public Resource findResourceById(String resId) {
        return resourceDao.findById(resId).orElseThrow(ResourceNotFoundException::new);
    }

	public Set<Resource> findResourceByRoleIds(List<String> resId) {
		return resourceDao.findResourceByRoleIds(resId);
	}

	public void saveOrUpdate(Resource resource) {
		resourceDao.save(resource);
	}

	public void deleteByIds(List<String> resId) {
		resourceDao.deleteBatch(resId);
	}
}
