package com.youyd.recruit.dao;

import com.youyd.recruit.pojo.Recruit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @description: dao
 * @author: LGG
 * @create: 2018-09-26 16:21
 **/


public interface RecruitDao {

	List findRecruitByCondition(Map map);

	Recruit findRecruitByPrimaryKey(String id);

	void deleteByPrimaryKey(List list);

	void insertRecruit(Recruit recruit);

	void updateByPrimaryKeySelective(Recruit recruit);

	List<Recruit> newJob(String status);
}
