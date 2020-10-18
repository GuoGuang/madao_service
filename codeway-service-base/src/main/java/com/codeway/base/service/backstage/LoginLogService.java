package com.codeway.base.service.backstage;

import com.codeway.api.user.UserServiceRpc;
import com.codeway.base.dao.LoginLogDao;
import com.codeway.base.mapper.LoginLogMapper;
import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.model.dto.base.LoginLogDto;
import com.codeway.model.dto.user.UserDto;
import com.codeway.model.pojo.base.LoginLog;
import com.codeway.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录日志
 **/
@Service
public class LoginLogService {

	private final LoginLogDao loginLogDao;
	private final LoginLogMapper loginLogMapper;

	private final UserServiceRpc userServiceRpc;

	public LoginLogService(LoginLogDao loginLogDao,
	                       LoginLogMapper loginLogMapper,
	                       UserServiceRpc userServiceRpc) {
		this.loginLogDao = loginLogDao;
		this.loginLogMapper = loginLogMapper;
		this.userServiceRpc = userServiceRpc;
	}

	/**
	 * 按照条件查询全部登录日志
	 *
	 * @return IPage<LoginLog>
	 */
	public Page<LoginLogDto> findLoginLogByCondition(LoginLogDto loginLogDto, Pageable pageable) {
		Specification<LoginLog> condition = (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(loginLogDto.getClientIp())) {
				predicates.add(builder.like(root.get("clientIp"), "%" + loginLogDto.getClientIp()));
			}
			return query.where(predicates.toArray(new Predicate[0])).getRestriction();
		};
		Page<LoginLogDto> queryResults = loginLogDao.findAll(condition, pageable)
				.map(loginLogMapper::toDto);
		queryResults.getContent().forEach(
				optLogList -> {
					UserDto userInfo = userServiceRpc.getUserInfoById(optLogList.getUserId())
							.getData();
					if (userInfo.getId().equals(optLogList.getUserId())) {
						optLogList.setUserName(userInfo.getUserName());
					}
				}
		);
        return queryResults;

	}

	/**
	 * 根据ID查询登录日志
	 *
	 * @param logId 登录日志id
	 * @return LoginLog
	 */
	public LoginLogDto findById(String logId) {
		return loginLogDao.findById(logId)
				.map(loginLogMapper::toDto)
				.orElseThrow(ResourceNotFoundException::new);
	}

	/**
	 * 添加登录日志
	 *
	 * @param loginLogDto 登录日志实体
	 */
	public void save(LoginLogDto loginLogDto) {
		if (StringUtils.isNotBlank(loginLogDto.getId())) {
			LoginLog tempLoginLog = loginLogDao.findById(loginLogDto.getId()).orElseThrow(ResourceNotFoundException::new);
			BeanUtil.copyProperties(tempLoginLog, loginLogDto);
		}
		loginLogDao.save(loginLogMapper.toEntity(loginLogDto));
	}

	public void deleteBatch(List<String> logId) {
		loginLogDao.deleteBatch(logId);
	}

}

