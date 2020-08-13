package com.codeway.base.service.backstage;

import com.codeway.api.user.UserServiceRpc;
import com.codeway.base.dao.LoginLogDao;
import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.model.pojo.base.LoginLog;
import com.codeway.model.pojo.user.User;
import com.codeway.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public LoginLogService(LoginLogDao loginLogDao, UserServiceRpc userServiceRpc) {
        this.loginLogDao = loginLogDao;
        this.userServiceRpc = userServiceRpc;
    }

    /**
     * 按照条件查询全部登录日志
     * @return IPage<LoginLog>
     */
    public Page<LoginLog> findLoginLogByCondition(LoginLog loginLog, Pageable pageable) {
        Specification<LoginLog> condition = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotEmpty(loginLog.getClientIp())) {
                predicates.add(builder.like(root.get("clientIp"), "%" + loginLog.getClientIp()));
            }
            return query.where(predicates.toArray(new Predicate[0])).getRestriction();
        };
        Page<LoginLog> queryResults = loginLogDao.findAll(condition, pageable);
	    List<User> userList = userServiceRpc.findUser().getData().getResults();
	    queryResults.getContent().forEach(
			    userLogList -> userList.forEach(
					    user -> {
						    if (user.getId().equals(userLogList.getUserId())) {
							    userLogList.setUserName(user.getUserName());
						    }
					    }
			    )
	    );
        return queryResults;

    }

	/**
	 * 根据ID查询登录日志
	 * @param logId 登录日志id
	 * @return LoginLog
	 */
	public LoginLog findById(String logId) {
        Optional<LoginLog> byId = loginLogDao.findById(logId);
        return byId.orElseThrow(ResourceNotFoundException::new);
    }

	/**
	 * 添加登录日志
	 * @param loginLog 登录日志实体
	 */
	public void save(LoginLog loginLog) {
		if (StringUtils.isNotBlank(loginLog.getId())){
			LoginLog tempLoginLog = loginLogDao.findById(loginLog.getId()).orElseThrow(ResourceNotFoundException::new);
			BeanUtil.copyProperties(tempLoginLog, loginLog);
		}
		loginLogDao.save(loginLog);
	}

	public void deleteBatch(List<String> logId) {
		loginLogDao.deleteBatch(logId);
	}

}

