package com.youyd.base.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.base.dao.DictDao;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.base.Dict;
import com.youyd.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 字典接口实现
 * @author: LGG
 * @create: 2018-09-27
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
		if (dict.getState() != null){
			queryWrapper.eq(Dict::getState,dict.getState());
		}
		IPage<Dict> dictIPage = dictDao.selectPage(pr, queryWrapper);
		return dictIPage;
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
}
