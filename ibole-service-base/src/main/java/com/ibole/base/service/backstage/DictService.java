package com.ibole.base.service.backstage;


import com.ibole.base.dao.DictDao;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.base.Dict;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 字典接口实现
 **/
@Service
public class DictService {

	private final DictDao dictDao;

	@Autowired
	public DictService(DictDao dictDao) {
		this.dictDao = dictDao;
	}

	/**
	 * 条件查询字典
	 *
	 * @param dict    字典实体
	 * @param queryVO 查询参数
	 * @return List
	 */
	public Page<Dict> findDictByCondition(Dict dict, QueryVO queryVO) {
		Specification<Dict> condition = (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(dict.getName())) {
				predicates.add(builder.like(root.get("name"), "%" + dict.getName() + "%"));
			}
			if (StringUtils.isNotEmpty(dict.getParentId())) {
				predicates.add(builder.equal(root.get("parentId"), dict.getParentId()));
			}
			if (dict.getState() != null) {
				predicates.add(builder.equal(root.get("state"), dict.getState()));
			}
			Predicate[] ps = new Predicate[predicates.size()];
			query.where(builder.and(predicates.toArray(ps)));
			query.orderBy(builder.desc(root.get("createAt").as(Long.class)));
			return null;
		};
		return dictDao.findAll(condition, queryVO.getPageable());
	}

	/**
	 * 按照字典类型获取树形字典
	 * @param dict 字典实体
	 * @return List
	 */
	public List<Dict> fetchDictTreeList(Dict dict) {
		return dictDao.findAllByType(dict.getType());
	}

	public Dict findDictById(String resId) {
		Optional<Dict> byId = dictDao.findById(resId);
		return byId.orElse(new Dict());
	}

	public void saveOrUpdate(Dict dict) {
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
