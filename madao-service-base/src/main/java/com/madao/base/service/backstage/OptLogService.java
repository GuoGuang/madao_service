package com.madao.base.service.backstage;

import com.madao.api.UserServiceRpc;
import com.madao.base.dao.OptLogDao;
import com.madao.base.mapper.OptLogMapper;
import com.madao.exception.custom.ResourceNotFoundException;
import com.madao.model.dto.base.OptLogDto;
import com.madao.model.dto.user.UserDto;
import com.madao.model.entity.base.OptLog;
import com.madao.utils.BeanUtil;
import com.madao.utils.JsonData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Service
public class OptLogService {

	private final OptLogDao optLogDao;
	private final OptLogMapper optLogMapper;
	private final UserServiceRpc userServiceRpc;

	public OptLogService(OptLogDao optLogDao,
	                     OptLogMapper optLogMapper,
	                     UserServiceRpc userServiceRpc) {
		this.optLogDao = optLogDao;
		this.optLogMapper = optLogMapper;
		this.userServiceRpc = userServiceRpc;
	}

	/**
	 * 按照条件查询全部操作日志
	 *
	 * @return IPage<OptLog>
	 */
	public Page<OptLogDto> findOptLogByCondition(OptLogDto optLogDto, Pageable pageable) {
		Specification<OptLog> condition = (root, query, builder) -> {
			List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(optLogDto.getClientIp())) {
				predicates.add(builder.like(root.get("clientIp"), "%" + optLogDto.getClientIp()));
			}
			return query.where(predicates.toArray(new javax.persistence.criteria.Predicate[0])).getRestriction();
		};
		Page<OptLogDto> queryResults = optLogDao.findAll(condition, pageable)
				.map(optLogMapper::toDto);

		JsonData<List<UserDto>> userInfoByIds = userServiceRpc.getUserInfoByIds(queryResults.getContent().stream()
				.map(OptLogDto::getUserId).toArray(String[]::new));

		if (userInfoByIds.isStatus()) {
			userInfoByIds.getData().stream().flatMap(userInfo -> queryResults.getContent().stream()
							.filter(articleId -> StringUtils.equals(userInfo.getId(), articleId.getUserId()))
							.peek(articleId -> articleId.setUserName(userInfo.getUserName())))
					.collect(Collectors.toList());
		}
		return queryResults;
	}

	/**
	 * 根据ID查询操作日志
	 *
	 * @param id 操作日志id
	 * @return OptLog
	 */
	public OptLogDto findById(String id) {
		return optLogDao.findById(id)
				.map(optLogMapper::toDto)
				.orElseThrow(ResourceNotFoundException::new);
	}

	/**
	 * 添加操作日志
	 *
	 * @param optLogDto 操作日志实体
	 */
	public void insertOptLog(OptLogDto optLogDto) {
		if (StringUtils.isNotBlank(optLogDto.getId())) {
			OptLog tempOptLog = optLogDao.findById(optLogDto.getId()).orElseThrow(ResourceNotFoundException::new);
			BeanUtil.copyProperties(tempOptLog, optLogDto);
		}
		optLogDao.save(optLogMapper.toEntity(optLogDto));
	}

	public void deleteBatch(List<String> resId) {
		optLogDao.deleteBatch(resId);
	}
}

