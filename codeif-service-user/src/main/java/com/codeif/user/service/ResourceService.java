package com.codeif.user.service;


import com.codeif.exception.custom.ResourceNotFoundException;
import com.codeif.pojo.QueryVO;
import com.codeif.pojo.user.Resource;
import com.codeif.user.dao.ResourceDao;
import com.codeif.utils.DateUtil;
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

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Autowired
    public ResourceService(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
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
			query.where(builder.and(predicates.toArray(ps)));
			query.orderBy(builder.desc(root.get("createAt").as(Long.class)));
			return null;
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
		if (StringUtils.isEmpty(resource.getId())) {
			resource.setCreateAt(DateUtil.getTimestamp());
			resource.setUpdateAt(DateUtil.getTimestamp());
			resourceDao.save(resource);
		} else {
			resourceDao.save(resource);
		}
	}

	public void deleteByIds(List<String> resId) {
		resourceDao.deleteBatch(resId);
	}
}
