package com.youyd.question.service.blog;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.pojo.QueryVO;
import com.youyd.question.dao.backstage.AnswersDao;
import com.youyd.question.pojo.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:  问题
 * @author: LGG
 * @create: 2019-02-26
 **/
@Service
public class BgAnswersService {

	@Autowired
	private AnswersDao answersDao;


	/**
	 * 查询全部列表
	 * @return IPage
	 */
	public IPage<Answers> findAnswersByCondition(QueryVO queryVO) {
		Page<Answers> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());
		QueryWrapper<Answers> queryWrapper = new QueryWrapper<>();
		return answersDao.selectPage(pr, queryWrapper);
	}

	/**
	 * 根据ID查询
	 * @param answersId:答案id
	 * @return Answers
	 */
	public Answers findAnswersByPrimaryKey(String answersId) {
		return answersDao.selectById(answersId);
	}

	/**
	 * 增加
	 * @param answers:答案实体
	 */
	public void insertAnswers(Answers answers) {
		answersDao.insert(answers);
	}

	/**
	 * 修改
	 * @param answers:答案实体
	 */
	public boolean updateByAnswersSelective(Answers answers) {
		int i = answersDao.updateById(answers);
		return SqlHelper.retBool(i);
	}

	/**
	 * 删除
	 * @param answersIds:要删除的数据数组
	 */
	public boolean deleteByPrimaryKey(List answersIds) {
		int i = answersDao.deleteBatchIds(answersIds);
		return SqlHelper.retBool(i);
	}
}
