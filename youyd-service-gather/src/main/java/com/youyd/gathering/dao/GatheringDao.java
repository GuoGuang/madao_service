package com.youyd.gathering.dao;

import com.youyd.gathering.pojo.Gathering;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @description: dao
 * @author: LGG
 * @create: 2018-09-26 16:21
 **/


public interface GatheringDao {

	List findGatheringByCondition(Map map);

	Gathering findGatheringByPrimaryKey(String id);

	void deleteByPrimaryKey(List list);

	void insertGathering(Gathering gathering);

	void updateByPrimaryKeySelective(Gathering gathering);

}
