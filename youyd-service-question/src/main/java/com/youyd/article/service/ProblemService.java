package com.youyd.article.service;

import com.youyd.article.dao.ProblemDao;
import com.youyd.article.pojo.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  问答板块:问题服务
 */

@Service
public class ProblemService {

	@Autowired
	private ProblemDao problemDao;


	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Problem> findProblemByCondition() {
		return problemDao.findProblemByCondition(null);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Problem findProblemByPrimaryKey(String id) {
		return problemDao.findProblemByPrimaryKey(id);
	}

	/**
	 * 增加
	 * @param problem
	 */
	public void insertProblem(Problem problem) {
		problemDao.insertProblem(problem);
	}

	/**
	 * 修改
	 * @param problem
	 */
	public void updateByPrimaryKeySelective(Problem problem) {
		problemDao.updateByPrimaryKeySelective(problem);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		problemDao.deleteByPrimaryKey(null);
	}

	/**
	 * 获取最新回复的问题列表
	 * @param labelid
	 * @param page
	 * @param size
	 * @return
	 */
	public Problem findNewRecommendList(String labelid,int page ,int size){
		//return problemDao.findNewRecommendList(labelid,pageRequest);
		return null;
	}

	/**
	 * 获取热门回复的问题列表
	 * @param labelid
	 * @param page
	 * @param size
	 * @return
	 */
	public Problem findHotRecommendList(String labelid,int page,int size){
		//1.创建分页对象
		//PageRequest pageRequest = PageRequest.of(page-1,size);
		//2.获取结果集并返回
		//return problemDao.findHotRecommendList(labelid,pageRequest);
        return null;
	}

	/**
	 * 获取等待回答的问题列表
	 * @param labelid
	 * @param page
	 * @param size
	 * @return
	 */
	public Problem findWaitRecommendList(String labelid,int page,int size){
		//1.创建分页对象
		//PageRequest pageRequest = PageRequest.of(page-1,size);
		//2.获取结果集并返回
		//return problemDao.findWaitRecommendList(labelid,pageRequest);
        return null;
	}
}
