package com.youyd.user.service;


import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.api.base.LoginLogServiceRpc;
import com.youyd.cache.constant.RedisConstant;
import com.youyd.cache.redis.RedisService;
import com.youyd.constant.CommonConst;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.base.LoginLog;
import com.youyd.pojo.user.Role;
import com.youyd.pojo.user.User;
import com.youyd.user.dao.UserDao;
import com.youyd.utils.*;
import com.youyd.utils.security.JWTAuthentication;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 用户服务
 * @author : LGG
 * @create : 2018-09-27
 **/
@Service
public class UserService {

	private final UserDao userDao;

	private final RedisService redisService;

	// jwt鉴权
	private final JWTAuthentication jwtAuthentication;
	// 加密
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	// 对象存储工具
	private final OssClientUtil ossClientUtil;

	private final LoginLogServiceRpc loginLogServiceRpc;


	@Autowired
	public UserService(UserDao userDao, RedisService redisService, JWTAuthentication jwtAuthentication, BCryptPasswordEncoder bCryptPasswordEncoder, OSSClient ossClient, OssClientUtil ossClientUtil, LoginLogServiceRpc loginLogServiceRpc) {
		this.userDao = userDao;
		this.redisService = redisService;
		this.jwtAuthentication = jwtAuthentication;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.ossClientUtil = ossClientUtil;
		this.loginLogServiceRpc = loginLogServiceRpc;
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
		if (StringUtils.isNotEmpty(user.getUserName())) {
			queryWrapper.eq(User::getUserName, user.getUserName());
		}
		if (user.getStatus() != null) {
			queryWrapper.eq(User::getStatus, user.getStatus());
		}
		queryWrapper.orderByDesc(User::getCreateAt);
		return userDao.selectPage(pr, queryWrapper);
	}

	/**
	 * 登录到系统
	 *  @param account  ：账号
	 * @param password ：密码
	 * @param request
	 */
	public Map login(String account, String password, HttpServletRequest request) {
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(User::getAccount, account);
		User uResult = userDao.selectOne(queryWrapper);
		if (uResult != null && bCryptPasswordEncoder.matches(password, uResult.getPassword())) {
			// 生成token
			String token = jwtAuthentication.createJWT(
									Long.valueOf(uResult.getId()),
									JsonUtil.toJsonString(uResult),
								"admin",
									DateUtil.getPlusWeeks(1));
			Map<String, String> map = new HashMap<>();
			map.put("token", token);
			map.put("user", JsonUtil.toJsonString(uResult));
			try {
				redisService.set(RedisConstant.REDIS_KEY_TOKEN + token, uResult, CommonConst.TIME_OUT_WEEK);
				// 添加登录日志
				LoginLog loginLog = new LoginLog();
				loginLog.setClientIp(HttpServletUtil.getIpAddr(request));
				loginLog.setUserId(uResult.getId());
				UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
				loginLog.setBrowser(userAgent.getBrowser().getName());
				loginLog.setOsInfo(userAgent.getOperatingSystem().getName());
				loginLog.setCreateAt(DateUtil.getTimestamp());
				loginLog.setUpdateAt(DateUtil.getTimestamp());
				loginLogServiceRpc.insertLoginLog(loginLog);

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
		User user = new User();
		user.setRoles(userDao.findRolesOfUser(id));
		user.setMenus(userDao.findMenusOfUser(id));
		return user;
	}

	public List<Role> getUseRoles(String id) {
		List<Role> rolesOfUser = userDao.findRolesOfUser(id);
		return rolesOfUser;
	}
}
