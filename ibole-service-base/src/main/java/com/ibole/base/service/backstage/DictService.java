package com.ibole.base.service.backstage;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.ibole.base.dao.DictDao;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.base.Dict;
import com.ibole.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 字典接口实现
 * @author LGG
 * @create 2018-09-27
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
	 * @param dict 字典实体
	 * @param queryVO 查询参数
	 * @return List
	 */
	public IPage<Dict> findDictByCondition(Dict dict, QueryVO queryVO) {
		Page<Dict> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());
		LambdaQueryWrapper<Dict> queryWrapper = new LambdaQueryWrapper<>();
		if (StringUtils.isNotEmpty(dict.getName())){
			queryWrapper.eq(Dict::getName,dict.getName());
		}
		if (StringUtils.isNotEmpty(dict.getParentId())){
			queryWrapper.eq(Dict::getParentId,dict.getParentId());
		}
		if (dict.getState() != null){
			queryWrapper.eq(Dict::getState,dict.getState());
		}
		queryWrapper.orderByDesc(Dict::getCreateAt);
		IPage<Dict> dictIPage = dictDao.selectPage(pr, queryWrapper);
		return dictIPage;
	}

	/**
	 * 按照字典类型获取树形字典
	 * @param dict 字典实体
	 * @return List
	 */
	public List<Dict> fetchDictTreeList(Dict dict) {
		LambdaQueryWrapper<Dict> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Dict::getType,dict.getType());
		return dictDao.selectList(queryWrapper);
	}

	public Dict findDictById(String resId) {
		return dictDao.selectById(resId);
	}

	public boolean updateByPrimaryKey(Dict resources) {
		int i = dictDao.updateById(resources);
		return SqlHelper.retBool(i);
	}

	public boolean insertDictSelective(Dict dict) {
		dict.setCreateAt(DateUtil.getTimestamp());
		dict.setUpdateAt(DateUtil.getTimestamp());
		int insert = dictDao.insert(dict);
		return SqlHelper.retBool(insert);
	}

	public boolean deleteDictByIds(List<String> resId) {
		int i = dictDao.deleteBatchIds(resId);
		return SqlHelper.retBool(i);
	}

	/**
	 * 获取组字典类型，所有根节点
	 * @param dict 资源实体
	 * @return JsonData
	 */
	public List<Dict> fetchDictType(Dict dict) {
		LambdaQueryWrapper<Dict> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.select(Dict::getId,Dict::getName,Dict::getType).eq(Dict::getParentId,"0");
		return dictDao.selectList(queryWrapper);
	}
}
