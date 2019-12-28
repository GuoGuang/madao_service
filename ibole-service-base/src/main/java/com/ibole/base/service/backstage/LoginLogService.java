package com.ibole.base.service.backstage;

import com.ibole.api.user.UserServiceRpc;
import com.ibole.base.dao.LoginLogDao;
import com.ibole.exception.custom.ResourceNotFoundException;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.base.LoginLog;
import com.ibole.pojo.base.QLoginLog;
import com.ibole.pojo.user.User;
import com.ibole.utils.QuerydslUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    JPAQueryFactory jpaQueryFactory;

    @Autowired
    public LoginLogService(LoginLogDao loginLogDao, UserServiceRpc userServiceRpc) {
        this.loginLogDao = loginLogDao;
        this.userServiceRpc = userServiceRpc;
    }

    /**
     * 按照条件查询全部登录日志
     *
     * @return IPage<LoginLog>
     */
    public QueryResults<LoginLog> findLoginLogByCondition(LoginLog loginLog, QueryVO queryVO) {

        QLoginLog qLoginLog = QLoginLog.loginLog;
        Predicate predicate = null;
        OrderSpecifier<?> sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qLoginLog);
        if (StringUtils.isNotEmpty(loginLog.getClientIp())) {
            predicate = ExpressionUtils.and(predicate, qLoginLog.clientIp.like(loginLog.getClientIp()));
        }
        if (StringUtils.isNotEmpty(queryVO.getFieldSort())) {
            sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qLoginLog, queryVO.getFieldSort());
        }
        QueryResults<LoginLog> queryResults = jpaQueryFactory
                .selectFrom(qLoginLog)
                .where(predicate)
                .offset(queryVO.getPageNum())
                .limit(queryVO.getPageSize())
                .orderBy(sortedColumn)
                .fetchResults();
        List<User> userList = userServiceRpc.findUser().getData().getResults();
        queryResults.getResults().forEach(
                loginLogList -> userList.forEach(
                        user -> {
                            if (user.getId().equals(loginLogList.getUserId())) {
                                loginLogList.setUserName(user.getUserName());
                            }
                        }
                )
        );
        return queryResults;
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

