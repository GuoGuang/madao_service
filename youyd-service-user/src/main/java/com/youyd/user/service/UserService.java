package com.youyd.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.user.User;

import java.util.List;
import java.util.Map;

/**
 * @description: 用户接口
 * @author: LGG
 * @create: 2018-09-27
 **/

public interface UserService{

	/**
	 * 注册用户
	 * @param user
	 */
	void insertUser(User user);

	IPage<User> findByCondition(User user, QueryVO queryVO);

	Map login(String account, String password);

	void logout(String token);

	boolean deleteByIds(List userId);

	boolean updateByPrimaryKey(User user);
}



