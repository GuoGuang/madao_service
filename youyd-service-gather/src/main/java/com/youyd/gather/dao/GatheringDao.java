package com.youyd.gather.dao;

import com.youyd.gather.pojo.Gathering;

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
