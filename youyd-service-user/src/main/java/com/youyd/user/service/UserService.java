package com.youyd.user.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.api.base.LoginLogServiceRpc;
import com.youyd.cache.redis.RedisService;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.user.Role;
import com.youyd.pojo.user.User;
import com.youyd.pojo.user.UserRole;
import com.youyd.user.dao.UserDao;
import com.youyd.user.dao.UserRoleDao;
import com.youyd.utils.DateUtil;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 用户服务
 * @author : LGG
 * @create : 2018-09-27
 **/
@Service
public class UserService {

	private final UserDao userDao;

	private final RedisService redisService;
	// 加密
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final LoginLogServiceRpc loginLogServiceRpc;

	private final UserRoleDao userRoleDao;


	@Autowired
	public UserService(UserDao userDao, RedisService redisService ,
	                   BCryptPasswordEncoder bCryptPasswordEncoder ,
	                   LoginLogServiceRpc loginLogServiceRpc, UserRoleDao userRoleDao) {
		this.userDao = userDao;
		this.redisService = redisService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.loginLogServiceRpc = loginLogServiceRpc;
		this.userRoleDao = userRoleDao;
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


	public IPage<User> findByCondition(User user, QueryVO queryVO ) {
		Page<User> pr = new Page<>(queryVO.getPageNum(), queryVO.getPageSize());
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(StringUtils.isNotEmpty(user.getUserName()),User::getUserName, user.getUserName());
		queryWrapper.eq(user.getStatus() != null,User::getStatus, user.getStatus());
		queryWrapper.eq(StringUtils.isNotBlank(user.getId()),User::getId,user.getId());
		queryWrapper.eq(StringUtils.isNotBlank(user.getAccount()),User::getAccount,user.getAccount());
		queryWrapper.eq(StringUtils.isNotBlank(user.getPhone()),User::getPhone,user.getPhone());
		queryWrapper.orderByDesc(User::getCreateAt);
		IPage<User> userIPage = userDao.selectPage(pr, queryWrapper);
		userIPage.getRecords().forEach(
				userResult -> userResult.setRoles(userDao.findRolesOfUser(userResult.getId()))
		);
		return userIPage;




	}

	public boolean deleteByIds(List<String> userId) {
		int i = userDao.deleteBatchIds(userId);
		return SqlHelper.retBool(i);
	}

	/**
	 * 更新用户基础信息，关联的角色
	 * @param user 用户实体
	 * @return boolean
	 */
	public boolean updateByPrimaryKey(User user) {
		int i = userDao.updateById(user);

		LambdaQueryWrapper<UserRole> deleteWrapper = new LambdaQueryWrapper<>();
		deleteWrapper.eq(UserRole::getUsUserId,user.getId());
		userRoleDao.delete(deleteWrapper);

		List<Role> roles = user.getRoles();
		for (Role role : roles) {
			UserRole userRole = new UserRole();
			userRole.setUsUserId(user.getId());
			userRole.setUsRoleId(role.getId());
			userRoleDao.insert(userRole);
		}
		return SqlHelper.retBool(i);
	}


	/**
	 * 修改密码
	 * @param user 当前用户
	 * @param oldPassword 老密码
	 * @return boolean
	 */
	public boolean changePassword(User user,String oldPassword) {
		User userInfo = userDao.selectById(user);
		if (!bCryptPasswordEncoder.matches(oldPassword, userInfo.getPassword())){
			return false;
		}
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userDao.updateById(user);
		return true;
	}

	/**
	 * 获取用户角色权限
	 * @param id 用户id
	 */
	public User getUserPermission(String id) {
		User user = userDao.selectById(id);
		user.setRoles(userDao.findRolesOfUser(id));
		user.setResource(userDao.findResourcesOfUser(id));
		return user;
	}

	public List<Role> getUseRoles(String id) {
		List<Role> rolesOfUser = userDao.findRolesOfUser(id);
		return rolesOfUser;
	}

	public User findUserByUserId(String userId) {
		User user = userDao.selectById(userId);
		user.setRoles(userDao.findRolesOfUser(user.getId()));
		return user;
	}

	public boolean updateUserProfile(User user) {
		int updateResult = userDao.updateById(user);
		return BooleanUtils.toBoolean(updateResult);
	}
}
