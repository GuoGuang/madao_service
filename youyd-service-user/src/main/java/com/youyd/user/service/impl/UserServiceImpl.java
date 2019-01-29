package com.youyd.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.cache.constant.RedisConstant;
import com.youyd.cache.redis.RedisService;
import com.youyd.user.dao.UserDao;
import com.youyd.user.pojo.User;
import com.youyd.user.service.UserService;
import com.youyd.utils.security.JWTAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 用户服务
 * @author: LGG
 * @create: 2018-09-27
 **/
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RedisService redisService;

	@Autowired
	private JWTAuthentication jwtAuthentication; // jwt鉴权

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; //加密

	/**
	 * 注册用户
	 * @param user
	 */
	@Override
	public void insertUser(User user) {
		//加密后的密码
		String bCryptPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(bCryptPassword);
		userDao.insert(user);
	}


	@Override
	public IPage<User> findByCondition(User user) {
		Page<User> pr = new Page<>(user.getPage(),user.getLimit());
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		return userDao.selectPage(pr, queryWrapper);
	}

	/**
	 * 登录到系统
	 * @param account ：账号
	 * @param password ：密码
	 */
	@Override
	public Map login(String account, String password) {
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(User::getAccount,account);
		User uResult = userDao.selectOne(queryWrapper);
		if( uResult!=null && bCryptPasswordEncoder.matches(password,uResult.getPassword())){
			// 生成token
			String token = jwtAuthentication.createJWT(uResult.getId(), uResult.getUserName(), "admin");
			Map<String, String> map = new HashMap<>();
			map.put("token", token);
			map.put("userName", uResult.getUserName());//昵称
			map.put("avatar", uResult.getAvatar());//头像
			redisService.set(RedisConstant.REDIS_KEY_TOKEN + token,uResult,RedisConstant.REDIS_TIME_WEEK);
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 登出系统
	 * @param token
	 */
	@Override
	public void logout(String token) {
		redisService.del(RedisConstant.REDIS_KEY_TOKEN + token);
	}

	@Override
	public boolean deleteByIds(List userId) {
		int i = userDao.deleteBatchIds(userId);
		return SqlHelper.retBool(i);
	}

	@Override
	public boolean updateByPrimaryKey(User user) {
		int i = userDao.updateById(user);
		return SqlHelper.retBool(i);
	}
}
