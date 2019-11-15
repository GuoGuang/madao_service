package com.ibole.base.service.backstage;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibole.api.user.UserServiceRpc;
import com.ibole.base.dao.LoginLogDao;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.base.LoginLog;
import com.ibole.pojo.user.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 登录日志
 * @author : LGG
 * @create : 2018-09-26 15:57
 **/
@Service
public class LoginLogService {

	private final LoginLogDao loginLogDao;

	private final UserServiceRpc userServiceRpc;

	@Autowired
	public LoginLogService(LoginLogDao loginLogDao,UserServiceRpc userServiceRpc) {
		this.loginLogDao = loginLogDao;
		this.userServiceRpc = userServiceRpc;
	}

	/**
	 * 按照条件查询全部登录日志
	 * @return IPage<LoginLog>
	 */
	public IPage<LoginLog> findLoginLogByCondition(LoginLog loginLog, QueryVO queryVO) {
		Page<LoginLog> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());
		LambdaQueryWrapper<LoginLog> queryWrapper = new LambdaQueryWrapper<>();
		if (StringUtils.isNotEmpty(loginLog.getClientIp())) {
			queryWrapper.like(LoginLog::getClientIp, loginLog.getClientIp());
		}
		queryWrapper.orderByDesc(LoginLog::getCreateAt);
		IPage<LoginLog> loginLogIPage = loginLogDao.selectPage(pr, queryWrapper);

		List<User> userList = userServiceRpc.findUser().getData().getRecords();
		loginLogIPage.getRecords().forEach(
				loginLogList -> userList.forEach(
						user -> {
							if (user.getId().equals(loginLogList.getUserId())){
								loginLogList.setUserName(user.getUserName());
							}
						}
				)
		);
		return loginLogIPage;
	}

	/**
	 * 根据ID查询登录日志
	 * @param id 登录日志id
	 * @return LoginLog
	 */
	public LoginLog findLoginLogByPrimaryKey(String id) {
		return loginLogDao.selectById(id);
	}

	/**
	 * 添加登录日志
	 * @param loginLog 登录日志实体
	 */
	public void insertLoginLog(LoginLog loginLog){
		loginLogDao.insert(loginLog);
	}


	/**
	 * 删除登录日志
	 * @param loginLogIds 要删除的id数组
	 */
	public void deleteById(List<String> loginLogIds){
		loginLogDao.deleteBatchIds(loginLogIds);
	}
}

