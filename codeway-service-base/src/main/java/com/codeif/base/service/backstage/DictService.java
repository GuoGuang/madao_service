package com.codeif.base.service.backstage;


import com.codeif.base.dao.DictDao;
import com.codeif.exception.custom.ResourceNotFoundException;
import com.codeif.pojo.QueryVO;
import com.codeif.pojo.base.Dict;
import com.codeif.pojo.base.QDict;
import com.codeif.utils.QuerydslUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 字典接口实现
 **/
@Service
public class DictService {

    private final DictDao dictDao;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

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
    public QueryResults<Dict> findDictByCondition(Dict dict, QueryVO queryVO) {

        QDict qDict = QDict.dict;
        Predicate predicate = null;
        OrderSpecifier<?> sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qDict);
        if (StringUtils.isNotEmpty(dict.getName())) {
            predicate = ExpressionUtils.and(predicate, qDict.name.like(dict.getName()));
        }
        if (StringUtils.isNotEmpty(dict.getParentId())) {
            predicate = ExpressionUtils.and(predicate, qDict.parentId.like(dict.getParentId()));
        }
        if (dict.getState() != null) {
            predicate = ExpressionUtils.and(predicate, qDict.state.eq(dict.getState()));
        }
        if (StringUtils.isNotEmpty(queryVO.getFieldSort())) {
            sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qDict, queryVO.getFieldSort());
        }
        QueryResults<Dict> queryResults = jpaQueryFactory
                .selectFrom(qDict)
                .where(predicate)
                .offset(queryVO.getPageNum())
                .limit(queryVO.getPageSize())
                .orderBy(sortedColumn)
                .fetchResults();
        return queryResults;
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
