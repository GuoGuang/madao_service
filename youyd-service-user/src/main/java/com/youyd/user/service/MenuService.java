package com.youyd.user.service;

import com.youyd.user.pojo.Menu;

import java.util.List;

/**
 * @description: 资源管理接口
 * @author: LGG
 * @create: 2018-12-23
 **/
public interface MenuService{

	/**
	 * 条件查询资源
	 * @param token 查询参数
	 * @return List
	 */
	List findMenuByCondition(String token);

	Menu findMenuById(String resId);

	boolean updateByPrimaryKey(Menu resources);

	boolean insertSelective(Menu resources);

	boolean deleteByIds(List resId);
}
