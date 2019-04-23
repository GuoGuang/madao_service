package com.youyd.user.service;


import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.cache.constant.RedisConstant;
import com.youyd.cache.redis.RedisService;
import com.youyd.pojo.user.User;
import com.youyd.user.dao.UserDao;
import com.youyd.utils.DateUtil;
import com.youyd.utils.LogBack;
import com.youyd.utils.OssClientUtil;
import com.youyd.utils.security.JWTAuthentication;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 用户服务
 * @author: LGG
 * @create: 2018-09-27
 **/
@Service
public class UserService {

	private final UserDao userDao;

	private final RedisService redisService;

	private final JWTAuthentication jwtAuthentication; // jwt鉴权

	private final BCryptPasswordEncoder bCryptPasswordEncoder; //加密

	private final OssClientUtil ossClientUtil; // 对象存储工具


	@Autowired
	public UserService(UserDao userDao, RedisService redisService, JWTAuthentication jwtAuthentication, BCryptPasswordEncoder bCryptPasswordEncoder, OSSClient ossClient, OssClientUtil ossClientUtil) {
		this.userDao = userDao;
		this.redisService = redisService;
		this.jwtAuthentication = jwtAuthentication;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.ossClientUtil = ossClientUtil;
	}

	/**
	 * 注册用户
	 *
	 * @param user
	 */
	public void insertUser(User user) {
		user.setCreateAt(DateUtil.getTimestamp());
		user.setUpdateAt(DateUtil.getTimestamp());
		//加密后的密码
		String bCryptPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(bCryptPassword);
		userDao.insert(user);
	}


	public IPage<User> findByCondition(User user) {
		Page<User> pr = new Page<>(user.getPageNum(), user.getPageSize());
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		if (StringUtils.isNotEmpty(user.getUserName())) {
			queryWrapper.eq(User::getUserName, user.getUserName());
		}
		if (user.getStatus() != null) {
			queryWrapper.eq(User::getStatus, user.getStatus());
		}
		return userDao.selectPage(pr, queryWrapper);
	}

	/**
	 * 登录到系统
	 *
	 * @param account  ：账号
	 * @param password ：密码
	 */
	public Map login(String account, String password) {
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(User::getAccount, account);
		User uResult = userDao.selectOne(queryWrapper);
		if (uResult != null && bCryptPasswordEncoder.matches(password, uResult.getPassword())) {
			// 生成token
			String token = jwtAuthentication.createJWT(Long.valueOf(uResult.getId()), uResult.getUserName(), "admin");
			Map<String, String> map = new HashMap<>();
			map.put("token", token);
			map.put("userName", uResult.getUserName());//昵称
			map.put("avatar", uResult.getAvatar());//头像
			try {
				redisService.set(RedisConstant.REDIS_KEY_TOKEN + token, uResult, RedisConstant.REDIS_TIME_WEEK);
			} catch (Exception ex) {
				LogBack.error(ex.getMessage(), ex);
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 登出系统
	 *
	 * @param token
	 */
	public void logout(String token) {
		redisService.del(RedisConstant.REDIS_KEY_TOKEN + token);
	}

	public boolean deleteByIds(List<String> userId) {
		int i = userDao.deleteBatchIds(userId);
		return SqlHelper.retBool(i);
	}

	public boolean updateByPrimaryKey(User user) {
		int i = userDao.updateById(user);
		return SqlHelper.retBool(i);
	}

	public User findUserById(String id) {
		return userDao.selectById(id);
	}

	/**
	 * 更新用户头像地址
	 */
	public void updateUserAvatar(User user, MultipartFile file) throws IOException {
		String fileUrl = ossClientUtil.uploadFile(file);
		user.setAvatar(fileUrl);
		userDao.updateById(user);
	}

	public boolean changePassword(User user) {
		return false;
	}
}
