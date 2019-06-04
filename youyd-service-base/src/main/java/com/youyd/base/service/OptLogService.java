package com.youyd.base.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youyd.base.dao.OptLogDao;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.base.OptLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作日志
 * @author : LGG
 * @create : 2018-09-26 15:57
 **/
@Service
public class OptLogService {

	private final OptLogDao optLogDao;

	@Autowired
	public OptLogService(OptLogDao optLogDao) {
		this.optLogDao = optLogDao;
	}

	/**
	 * 按照条件查询全部操作日志
	 * @return IPage<OptLog>
	 */
	public IPage<OptLog> findOptLogByCondition(OptLog optLog, QueryVO queryVO) {
		Page<OptLog> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());
		LambdaQueryWrapper<OptLog> queryWrapper = new LambdaQueryWrapper<>();
		if (StringUtils.isNotEmpty(optLog.getClientIp())) {
			queryWrapper.like(OptLog::getClientIp, optLog.getClientIp());
		}
		queryWrapper.orderByDesc(OptLog::getCreateAt);
		IPage<OptLog> optLogIPage = optLogDao.selectPage(pr, queryWrapper);
		return optLogIPage;
	}

	/**
	 * 根据ID查询操作日志
	 * @param id 操作日志id
	 * @return OptLog
	 */
	public OptLog findOptLogByPrimaryKey(String id) {
		return optLogDao.selectById(id);
	}

	/**
	 * 添加操作日志
	 * @param optLog 操作日志实体
	 */
	public void insertOptLog(OptLog optLog){
		optLogDao.insert(optLog);
	}


	/**
	 * 删除操作日志
	 * @param optLogIds 要删除的id数组
	 */
	public void deleteById(List<String> optLogIds){
		optLogDao.deleteBatchIds(optLogIds);
	}
}

