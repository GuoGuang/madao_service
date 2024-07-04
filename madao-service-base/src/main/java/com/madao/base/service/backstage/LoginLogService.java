package com.madao.base.service.backstage;

import com.madao.api.UserServiceRpc;
import com.madao.base.mapper.LoginLogMapper;
import com.madao.base.repository.LoginLogRepository;
import com.madao.exception.custom.ResourceNotFoundException;
import com.madao.model.dto.base.LoginLogDto;
import com.madao.model.dto.user.UserDto;
import com.madao.model.entity.base.LoginLog;
import com.madao.utils.BeanUtil;
import com.madao.utils.JsonData;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录日志
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Service
@AllArgsConstructor
public class LoginLogService {

	private final LoginLogRepository loginLogRepository;
	private final LoginLogMapper loginLogMapper;

	private final UserServiceRpc userServiceRpc;

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
		Page<LoginLogDto> queryResults = loginLogRepository.findAll(condition, pageable)
				.map(loginLogMapper::toDto);
		JsonData<List<UserDto>> userInfoByIds = userServiceRpc.getUserInfoByIds(queryResults.getContent().stream()
				.map(LoginLogDto::getUserId).toArray(String[]::new));

		if (userInfoByIds.isStatus()) {
			userInfoByIds.getData().stream().flatMap(userInfo -> queryResults.getContent().stream()
							.filter(articleId -> StringUtils.equals(userInfo.getId(), articleId.getUserId()))
							.peek(articleId -> articleId.setUserName(userInfo.getUserName())))
					.toList();
		}
		return queryResults;

	}

	/**
	 * 根据ID查询登录日志
	 *
	 * @param logId 登录日志id
	 * @return LoginLog
	 */
	public LoginLogDto findById(String logId) {
		return loginLogRepository.findById(logId)
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
			LoginLog tempLoginLog = loginLogRepository.findById(loginLogDto.getId()).orElseThrow(ResourceNotFoundException::new);
			BeanUtil.copyProperties(tempLoginLog, loginLogDto);
		}
		loginLogRepository.save(loginLogMapper.toEntity(loginLogDto));
	}

	public void deleteBatch(List<String> logId) {
		loginLogRepository.deleteBatch(logId);
	}

}

