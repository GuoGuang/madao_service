package com.ibole.base.service.backstage;


import com.aliyun.oss.ServiceException;
import com.ibole.base.dao.JobDao;
import com.ibole.pojo.QuartzJob;
import com.ibole.pojo.QueryVO;
import com.ibole.utils.DateUtil;
import com.ibole.utils.QuartzUtil;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 任务调度
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
	 *
	 * @param quartzJob 资源实体
	 * @param queryVO   查询参数
	 * @return List
	 */
	public Page<QuartzJob> findTaskByCondition(QuartzJob quartzJob, QueryVO queryVO) {
		Specification<QuartzJob> condition = (root, query, builder) -> {
			query.orderBy(builder.desc(root.get("createAt").as(Long.class)));
			return null;
		};
		return jobDao.findAll(condition, queryVO.getPageable());

	}

	public QuartzJob findJobById(String resId) {
		return jobDao.findById(resId).get();
	}

	public void updateByPrimaryKey(QuartzJob quartzJob) {
		jobDao.save(quartzJob);
		QuartzUtil.updateQuartzJob(scheduler, quartzJob);
	}

	public void insertSelective(QuartzJob quartzJob) {
		quartzJob.setCreateAt(DateUtil.getTimestamp());
		quartzJob.setUpdateAt(DateUtil.getTimestamp());
		jobDao.save(quartzJob);
		QuartzUtil.createQuartzJob(scheduler, quartzJob);
	}

	public void deleteByIds(List<String> quartzJobs) {
		jobDao.deleteBatch(quartzJobs);
		for (String quartzJob : quartzJobs) {
			Optional<QuartzJob> quartzJobInfo = jobDao.findById(quartzJob);
			QuartzUtil.deleteJob(scheduler, quartzJobInfo.get());
		}
	}

	/**
	 * 获取所有的job
	 *
	 * @return List<QuartzJob>
	 */
	public List<QuartzJob> getAllJobByCondition(QuartzJob quartzJob) {

		Specification<QuartzJob> condition = (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (quartzJob.getEnable() == 1) {
				predicates.add(builder.equal(root.get("enable"), 1));
			}
			Predicate[] ps = new Predicate[predicates.size()];
			query.where(builder.and(predicates.toArray(ps)));
			query.orderBy(builder.desc(root.get("createAt").as(Long.class)));
			return null;
		};

		return jobDao.findAll(condition);
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
	private QuartzJob updateQuartzJobStatus(String jobId, Integer isPause) {
		Optional<QuartzJob> quartzJob = jobDao.findById(jobId);
		if (quartzJob.isPresent()) {
			QuartzJob qj = quartzJob.get();
			qj.setPause(isPause);
			jobDao.save(qj);
		}
		return quartzJob.get();
	}

}
