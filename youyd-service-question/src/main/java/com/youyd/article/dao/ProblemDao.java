package com.youyd.article.dao;

import com.youyd.article.pojo.Problem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @description: dao
 * @author: LGG
 * @create: 2018-09-26 16:21
 **/


public interface ProblemDao {

	List findProblemByCondition(Map map);

	Problem findProblemByPrimaryKey(String id);

	void deleteByPrimaryKey(List list);

	void insertProblem(Problem problem);

	void updateByPrimaryKeySelective(Problem problem);

	/**
	 * 查询最新回答的问题列表（查的是问题列表）
	 */
	//@Query("select p from Problem p where p.id in (select problemid from Pl where labelid = ?1) and p.replytime is not null order by p.replytime desc")
	//Page<Problem> findNewRecommendList(String labelid, Pageable pageable);

}
