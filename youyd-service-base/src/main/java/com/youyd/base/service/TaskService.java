package com.youyd.base.service;


import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.base.dao.JobDao;
import com.youyd.pojo.QuartzJob;
import com.youyd.pojo.QueryVO;
import com.youyd.utils.DateUtil;
import com.youyd.utils.QuartzUtil;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务调度
 * @author : LGG
 * @create : 201-05-04
 **/
@Service
public class TaskService {

	private final JobDao jobDao;
	@Autowired
	private Scheduler scheduler;

	@Autowired
	public TaskService(JobDao jobDao) {
		this.jobDao = jobDao;
	}

	/**
	 * 条件查询任务列表
	 * @param quartzJob 菜单实体
	 * @param queryVO 查询参数
	 * @return List
	 */
	public IPage<QuartzJob> findTaskByCondition(QuartzJob quartzJob, QueryVO queryVO) {
		Page<QuartzJob> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());
		LambdaQueryWrapper<QuartzJob> queryWrapper = new LambdaQueryWrapper<>();
		IPage<QuartzJob> taskIPage = jobDao.selectPage(pr, queryWrapper);
		return taskIPage;
	}

	public QuartzJob findJobById(String resId) {
		return jobDao.selectById(resId);
	}

	public boolean updateByPrimaryKey(QuartzJob quartzJob) {
		int i = jobDao.updateById(quartzJob);

		QuartzUtil.updateQuartzJob(scheduler, quartzJob);
		return SqlHelper.retBool(i);
	}

	public boolean insertSelective(QuartzJob quartzJob) {
		quartzJob.setCreateAt(DateUtil.getTimestamp());
		quartzJob.setUpdateAt(DateUtil.getTimestamp());
		int insert = jobDao.insert(quartzJob);
		QuartzUtil.createQuartzJob(scheduler, quartzJob);
		return SqlHelper.retBool(insert);
	}

	public boolean deleteByIds(List<String> quartzJobs) {
		int i = jobDao.deleteBatchIds(quartzJobs);
		for (String quartzJob : quartzJobs) {
			QuartzJob quartzJobInfo = jobDao.selectById(quartzJob);
			QuartzUtil.deleteJob(scheduler, quartzJobInfo);
		}
		return SqlHelper.retBool(i);
	}

	/**
	 * 获取所有的job
	 * @return List<QuartzJob>
	 */
	public List<QuartzJob> getAllJobByCondition(QuartzJob quartzJob) {
		LambdaQueryWrapper<QuartzJob> queryWrapper = new LambdaQueryWrapper<>();
		if (quartzJob.getEnable() == 1){
			queryWrapper.eq(QuartzJob::getEnable,1);
		}
		return jobDao.selectList(queryWrapper);
	}

	/**
	 * 恢复任务，重新开启
	 * @param jobId id
	 * @return boolean
	 */
	public boolean resume(String jobId){
		QuartzJob quartzJob = updateQuartzJobStatus(jobId, 0);
		QuartzUtil.resumeJob(scheduler, quartzJob);
		return true;
	}

	/**
	 * 暂停任务
	 * @param jobId
	 * @return
	 * @throws ServiceException
	 */
	public boolean pause(String jobId) throws ServiceException {
		QuartzJob quartzJob = updateQuartzJobStatus(jobId, 1);
		QuartzUtil.pauseJob(scheduler, quartzJob);
		return true;
	}

	/**
	 * 启动任务
	 * @param jobId id
	 * @return boolean
	 */
	public boolean run(String jobId) throws ServiceException {
		QuartzJob quartzJob = updateQuartzJobStatus(jobId, 0);
		QuartzUtil.run(scheduler, quartzJob);
		return true;
	}


	/**
	 * 更新Job状态
	 * @param jobId job id
	 * @param isPause 是否暂停
	 * @return QuartzJob
	 */
	private QuartzJob updateQuartzJobStatus(String jobId, Integer isPause){
		QuartzJob quartzJob = jobDao.selectById(jobId);
		quartzJob.setPause(isPause);
		updateByPrimaryKey(quartzJob);
		return quartzJob;
	}

}
