package com.youyd.recruit.service;

import com.youyd.pojo.Result;
import com.youyd.utils.CodeCommonUtil;
import com.youyd.utils.StatusCode;
import com.youyd.recruit.dao.RecruitDao;
import com.youyd.recruit.pojo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @description: 招聘服务
 * @author: LGG
 * @create: 2018-09-27
 **/
@Service
public class RecruitService {

	@Autowired
	private RecruitDao recruitDao;

	/**
	 * 按照条件查询全部标签
	 * @param map
	 * @param page
	 * @param size
	 * @return
	 */
	public Result findRecruitByCondition(Map map, Integer page, Integer size) {
		List result = recruitDao.findRecruitByCondition(map);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
	}

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public Recruit findRecruitByPrimaryKey(String id) {
		return recruitDao.findRecruitByPrimaryKey(id);
	}

	/**
	 * 增加
	 * @param
	 */
	public void insertRecruit(Recruit recruit) {
		recruitDao.insertRecruit(recruit);
	}

	/**
	 * 修改
	 * @param
	 */
	public void updateByPrimaryKeyfindive(Recruit recruit) {
		recruitDao.updateByPrimaryKeySelective(recruit);
	}

	/**
	 * 删除
	 * @param ids
	 */
	public void deleteById(String ids) {
		List<String> delIds = CodeCommonUtil.deletePart(ids);
		recruitDao.deleteByPrimaryKey(delIds);
	}

	/**
	 * 最新职位列表,查询状态不为0并以创建日期降序排序，查询前12条记录
	 * @return
	 */
	public List<Recruit> newJob(){
		return recruitDao.newJob("0");//状态为0的是不可用的
	}

	/**
	 * 推荐职位列表
	 * @return
	 */
	public List<Recruit> reCommendList(){
		return null;
		// return recruitDao.findTop4ByStateOrderByCreatetimeDesc("2");//2表示推荐  0表示关闭 1表示开启
	}
}



