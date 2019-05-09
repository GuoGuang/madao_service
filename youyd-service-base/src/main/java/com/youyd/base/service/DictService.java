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
	 * @param menu 菜单实体
	 * @param queryVO 查询参数
	 * @return List
	 */
	public IPage<Dict> findDictByCondition(Dict menu, QueryVO queryVO) {
		Page<Dict> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());
		LambdaQueryWrapper<Dict> queryWrapper = new LambdaQueryWrapper<>();
		if (StringUtils.isNotEmpty(menu.getName())){
			queryWrapper.eq(Dict::getName,menu.getName());
		}
		if (menu.getState() != null){
			queryWrapper.eq(Dict::getState,menu.getState());
		}
		IPage<Dict> menuIPage = dictDao.selectPage(pr, queryWrapper);
		return menuIPage;
	}

	public Dict findDictById(String resId) {
		return dictDao.selectById(resId);
	}

	public boolean updateByPrimaryKey(Dict resources) {
		int i = dictDao.updateById(resources);
		return SqlHelper.retBool(i);
	}

	public boolean insertDictSelective(Dict menu) {
		menu.setCreateAt(DateUtil.getTimestamp());
		menu.setUpdateAt(DateUtil.getTimestamp());
		int insert = dictDao.insert(menu);
		return SqlHelper.retBool(insert);
	}

	public boolean deleteDictByIds(List<String> resId) {
		int i = dictDao.deleteBatchIds(resId);
		return SqlHelper.retBool(i);
	}
}
