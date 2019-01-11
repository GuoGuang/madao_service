package com.youyd.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.user.dao.MenuDao;
import com.youyd.user.pojo.Menu;
import com.youyd.user.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 资源接口实现
 * @author: LGG
 * @create: 2018-09-27
 **/
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	/**
	 * 条件查询资源
	 * @param token 查询参数
	 * @return List
	 */
	@Override
	public List findMenuByCondition(String token) {
		QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
		return menuDao.selectList(queryWrapper);
	}

	@Override
	public Menu findMenuById(String resId) {
		return menuDao.selectById(resId);
	}

	@Override
	public boolean updateByPrimaryKey(Menu resources) {
		int i = menuDao.updateById(resources);
		return SqlHelper.retBool(i);
	}

	@Override
	public boolean insertSelective(Menu resources) {
		int insert = menuDao.insert(resources);
		return SqlHelper.retBool(insert);
	}

	@Override
	public boolean deleteByIds(List resId) {
		int i = menuDao.deleteBatchIds(resId);
		return SqlHelper.retBool(i);
	}
}
