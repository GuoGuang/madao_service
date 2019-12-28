package com.ibole.base.service.backstage;

import com.ibole.api.user.UserServiceRpc;
import com.ibole.base.dao.OptLogDao;
import com.ibole.exception.custom.ResourceNotFoundException;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.base.OptLog;
import com.ibole.pojo.base.QOptLog;
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
 * 操作日志
 **/
@Service
public class OptLogService {

    private final OptLogDao optLogDao;
    private final UserServiceRpc userServiceRpc;
    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Autowired
    public OptLogService(OptLogDao optLogDao, UserServiceRpc userServiceRpc) {
        this.optLogDao = optLogDao;
        this.userServiceRpc = userServiceRpc;
    }

    /**
     * 按照条件查询全部操作日志
     *
     * @return IPage<OptLog>
     */
    public QueryResults<OptLog> findOptLogByCondition(OptLog optLog, QueryVO queryVO) {
        QOptLog qOptLog = QOptLog.optLog;
        Predicate predicate = null;
        OrderSpecifier<?> sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qOptLog);
        if (StringUtils.isNotEmpty(optLog.getClientIp())) {
            predicate = ExpressionUtils.and(predicate, qOptLog.clientIp.like(optLog.getClientIp()));
        }
        if (StringUtils.isNotEmpty(queryVO.getFieldSort())) {
            sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qOptLog, queryVO.getFieldSort());
        }
        QueryResults<OptLog> queryResults = jpaQueryFactory
                .selectFrom(qOptLog)
                .where(predicate)
                .offset(queryVO.getPageNum())
                .limit(queryVO.getPageSize())
                .orderBy(sortedColumn)
                .fetchResults();

        List<User> userList = userServiceRpc.findUser().getData().getResults();
        queryResults.getResults().forEach(
                optLogList -> userList.forEach(
                        user -> {
                            if (user.getId().equals(optLogList.getUserId())) {
                                optLogList.setUserName(user.getUserName());
                            }
                        }
                )
        );
        return queryResults;
    }

	/**
	 * 根据ID查询操作日志
	 *
	 * @param id 操作日志id
	 * @return OptLog
	 */
	public OptLog findById(String id) {
		Optional<OptLog> byId = optLogDao.findById(id);
		return byId.orElseThrow(ResourceNotFoundException::new);
	}

	/**
	 * 添加操作日志
	 *
	 * @param optLog 操作日志实体
	 */
	public void insertOptLog(OptLog optLog) {
		optLogDao.save(optLog);
	}

	public void deleteBatch(List<String> resId) {
		optLogDao.deleteBatch(resId);
	}
}

