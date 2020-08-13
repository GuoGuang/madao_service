package com.codeway.base.service.backstage;


import com.codeway.base.dao.DictDao;
import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.model.pojo.base.Dict;
import com.codeway.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 字典接口实现
 **/
@Service
public class DictService {

    private final DictDao dictDao;

    public DictService(DictDao dictDao) {
        this.dictDao = dictDao;
    }

    /**
     * 条件查询字典
     * @param dict    字典实体
     * @return List
     */
    public Page<Dict> findDictByCondition(Dict dict, Pageable pageable) {
        Specification<Dict> condition = (root, query, builder) -> {
            List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotEmpty(dict.getName())) {
                predicates.add(builder.like(root.get("name"), "%" + dict.getName() + "%"));
            }
            if (StringUtils.isNotEmpty(dict.getParentId())) {
                predicates.add(builder.equal(root.get("parentId"), dict.getParentId()));
            }
            return query.where(predicates.toArray(new javax.persistence.criteria.Predicate[0])).getRestriction();
        };
        return dictDao.findAll(condition, pageable);
    }

    /**
     * 按照字典类型获取树形字典
     *
     * @param dict 字典实体
     * @return List
     */
    public List<Dict> fetchDictTreeList(Dict dict) {
        return dictDao.findAllByType(dict.getType());
    }

    public Dict findDictById(String resId) {
        Optional<Dict> byId = dictDao.findById(resId);
        return byId.orElseThrow(ResourceNotFoundException::new);
    }

    public void saveOrUpdate(Dict dict) {
	    if (StringUtils.isNotBlank(dict.getId())){
		    Dict tempDict = dictDao.findById(dict.getId()).orElseThrow(ResourceNotFoundException::new);
		    BeanUtil.copyProperties(tempDict, dict);
	    }
        dictDao.save(dict);
    }

	public void deleteBatch(List<String> resId) {
		dictDao.deleteBatch(resId);
	}

	/**
	 * 获取组字典类型，所有根节点
	 *
	 * @param dict 资源实体
	 * @return JsonData
	 */
	public List<Dict> findIdNameTypeByParentId(Dict dict) {
		return dictDao.findByParentId("0");
	}
}
