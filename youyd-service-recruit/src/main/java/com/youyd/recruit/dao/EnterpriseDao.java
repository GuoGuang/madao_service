package com.youyd.recruit.dao;

import com.youyd.recruit.pojo.Enterprise;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @description: dao
 * @author: LGG
 * @create: 2018-09-26 16:21
 **/


public interface EnterpriseDao {

	List findEnterpriseByCondition(Map map);

	Enterprise findEnterpriseByPrimaryKey(String id);

	void deleteByPrimaryKey(List list);

	void insertEnterprise(Enterprise enterprise);

	void updateByPrimaryKeySelective(Enterprise enterprise);

}
