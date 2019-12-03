package com.ibole.base.service.backstage;

import com.ibole.api.user.UserServiceRpc;
import com.ibole.base.dao.OptLogDao;
import com.ibole.exception.custom.ResourceNotFoundException;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.base.OptLog;
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
 * 操作日志
 **/
@Service
public class OptLogService {

	private final OptLogDao optLogDao;
	private final UserServiceRpc userServiceRpc;
	@Autowired
	public OptLogService(OptLogDao optLogDao,UserServiceRpc userServiceRpc) {
		this.optLogDao = optLogDao;
		this.userServiceRpc = userServiceRpc;
	}

	/**
	 * 按照条件查询全部操作日志
	 *
	 * @return IPage<OptLog>
	 */
	public Page<OptLog> findOptLogByCondition(OptLog optLog, QueryVO queryVO) {

		Specification<OptLog> condition = (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(optLog.getClientIp())) {
				predicates.add(builder.like(root.get("clientIp"), "%" + optLog.getClientIp() + "%"));
			}
			Predicate[] ps = new Predicate[predicates.size()];
			query.where(builder.and(predicates.toArray(ps)));
			query.orderBy(builder.desc(root.get("createAt").as(Long.class)));
			return null;
		};

		Page<OptLog> optLogIPage = optLogDao.findAll(condition, queryVO.getPageable());
		List<User> userList = userServiceRpc.findUser().getData().getContent();
		optLogIPage.getContent().forEach(
				optLogList -> userList.forEach(
						user -> {
							if (user.getId().equals(optLogList.getUserId())) {
								optLogList.setUserName(user.getUserName());
							}
						}
				)
		);
		return optLogIPage;
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

