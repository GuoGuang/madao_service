package com.youyd.question.service.backstage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.pojo.QueryVO;
import com.youyd.question.dao.backstage.QuestionDao;
import com.youyd.question.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 问题服务
 * @author: LGG
 * @create: 2019-02-26 16:21
 **/
@Service
public class QuestionService {

	@Autowired
	private QuestionDao questionDao;


	/**
	 * 查询全部列表
	 * @return
	 */
	public IPage<Question> findQuestionByCondition(QueryVO queryVO) {
		Page<Question> pr = new Page<>(queryVO.getPageSize(),queryVO.getPageSize());
		QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
		return questionDao.selectPage(pr, queryWrapper);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Question findQuestionByPrimaryKey(String questionId) {
		Question question = questionDao.selectById(questionId);

		return null;
	}

	/**
	 * 增加
	 * @param Question
	 */
	public boolean insertQuestion(Question question) {
		int insert = questionDao.insert(question);
		return SqlHelper.retBool(insert);
	}

	/**
	 * 修改
	 * @param Question
	 */
	public boolean updateByPrimaryKeySelective(Question question) {
		int i = questionDao.updateById(question);
		return SqlHelper.retBool(i);
	}

	/**
	 * 删除
	 * @param id
	 */
	public boolean deleteByIds(List questionId) {
		int i = questionDao.deleteBatchIds(questionId);
		return SqlHelper.retBool(i);
	}

	/**
	 * 获取最新回复的问题列表
	 * @param labelid
	 * @param page
	 * @param size
	 * @return
	 */
	public Question findNewRecommendList(String labelid,int page ,int size){
		//return QuestionDao.findNewRecommendList(labelid,pageRequest);
		return null;
	}

	/**
	 * 获取热门回复的问题列表
	 * @param labelid
	 * @param page
	 * @param size
	 * @return
	 */
	public Question findHotRecommendList(String labelid,int page,int size){
		//1.创建分页对象
		//PageRequest pageRequest = PageRequest.of(page-1,size);
		//2.获取结果集并返回
		//return QuestionDao.findHotRecommendList(labelid,pageRequest);
        return null;
	}

	/**
	 * 获取等待回答的问题列表
	 * @param labelid
	 * @param page
	 * @param size
	 * @return
	 */
	public Question findWaitRecommendList(String labelid,int page,int size){
		//1.创建分页对象
		//PageRequest pageRequest = PageRequest.of(page-1,size);
		//2.获取结果集并返回
		//return QuestionDao.findWaitRecommendList(labelid,pageRequest);
        return null;
	}
}
