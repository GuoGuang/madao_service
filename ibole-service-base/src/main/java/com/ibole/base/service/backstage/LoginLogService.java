package com.ibole.base.service.backstage;

import com.ibole.api.user.UserServiceRpc;
import com.ibole.base.dao.LoginLogDao;
import com.ibole.exception.custom.ResourceNotFoundException;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.base.LoginLog;
import com.ibole.pojo.user.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 登录日志
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
	 *
	 * @return IPage<LoginLog>
	 */
	public Page<LoginLog> findLoginLogByCondition(LoginLog loginLog, QueryVO queryVO) {

		Specification<LoginLog> condition = (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(loginLog.getClientIp())) {
				predicates.add(builder.like(root.get("clientIp"), "%" + loginLog.getClientIp() + "%"));
			}
			Predicate[] ps = new Predicate[predicates.size()];
			query.where(builder.and(predicates.toArray(ps)));
			query.orderBy(builder.desc(root.get("createAt").as(Long.class)));
			return null;
		};

		Page<LoginLog> loginLogPage = loginLogDao.findAll(condition, queryVO.getPageable());
		List<User> userList = userServiceRpc.findUser().getData().getContent();
		loginLogPage.getContent().forEach(
				loginLogList -> userList.forEach(
						user -> {
							if (user.getId().equals(loginLogList.getUserId())) {
								loginLogList.setUserName(user.getUserName());
							}
						}
				)
		);

		return loginLogPage;
	}

	/**
	 * 根据ID查询登录日志
	 *
	 * @param logId 登录日志id
	 * @return LoginLog
	 */
	public LoginLog findById(String logId) {
        Optional<LoginLog> byId = loginLogDao.findById(logId);
        return byId.orElseThrow(ResourceNotFoundException::new);
    }

	/**
	 * 添加登录日志
	 *
	 * @param loginLog 登录日志实体
	 */
	public void save(LoginLog loginLog) {
		loginLogDao.save(loginLog);
	}

	public void deleteBatch(List<String> logId) {
		loginLogDao.deleteBatch(logId);
	}

}

