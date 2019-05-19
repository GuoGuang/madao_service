package com.youyd.user.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.base.Menu;
import com.youyd.user.dao.MenuDao;
import com.youyd.utils.DateUtil;
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

	private final MenuDao menuDao;

	@Autowired
	public MenuService(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	/**
	 * 条件查询资源
	 * @param menu 菜单实体
	 * @param queryVO 查询参数
	 * @return List
	 */
	public IPage<Menu> findMenuByCondition(Menu menu, QueryVO queryVO) {
		Page<Menu> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());
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

	public boolean insertSelective(Menu menu) {
		menu.setCreateAt(DateUtil.getTimestamp());
		menu.setUpdateAt(DateUtil.getTimestamp());
		int insert = menuDao.insert(menu);
		return SqlHelper.retBool(insert);
	}

	public boolean deleteByIds(List<String> resId) {
		int i = menuDao.deleteBatchIds(resId);
		return SqlHelper.retBool(i);
	}
}
