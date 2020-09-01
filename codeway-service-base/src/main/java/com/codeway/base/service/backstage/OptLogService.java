package com.codeway.base.service.backstage;

import com.codeway.api.user.UserServiceRpc;
import com.codeway.base.dao.OptLogDao;
import com.codeway.base.mapper.OptLogMapper;
import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.model.dto.base.OptLogDto;
import com.codeway.model.dto.user.UserDto;
import com.codeway.model.pojo.base.OptLog;
import com.codeway.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
		queryResults.getContent().forEach(
				optLogList -> {
					UserDto userInfo = userServiceRpc.getUserInfo(
							new UserDto(optLogList.getUserId()))
							.getData();
					if (userInfo.getId().equals(optLogList.getUserId())) {
						optLogList.setUserName(userInfo.getUserName());
					}
				}
		);
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

