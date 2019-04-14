package com.youyd.base.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.base.dao.MenuDao;
import com.youyd.pojo.base.Menu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 资源接口实现
 * @author: LGG
 * @create: 2018-09-27
 **/
@Service
public class MenuService{

	@Autowired
	private MenuDao menuDao;

	/**
	 * 条件查询资源
	 * @param token 查询参数
	 * @return List
	 */
	public IPage<Menu> findMenuByCondition(Menu menu) {
		Page<Menu> pr = new Page<>(menu.getPageNum(),menu.getPageSize());
		LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
		if (StringUtils.isNotEmpty(menu.getName())){
			queryWrapper.eq(Menu::getName,menu.getName());
		}
		if (menu.getStatus() != null){
			queryWrapper.eq(Menu::getStatus,menu.getStatus());
		}


		IPage<Menu> menuIPage = menuDao.selectPage(pr, queryWrapper);
		return menuIPage;
	}

	public Menu findMenuById(String resId) {
		return menuDao.selectById(resId);
	}

	public boolean updateByPrimaryKey(Menu resources) {
		int i = menuDao.updateById(resources);
		return SqlHelper.retBool(i);
	}

	public boolean insertSelective(Menu resources) {
		int insert = menuDao.insert(resources);
		return SqlHelper.retBool(insert);
	}

	public boolean deleteByIds(List<Long>  resId) {
		int i = menuDao.deleteBatchIds(resId);
		return SqlHelper.retBool(i);
	}
}
