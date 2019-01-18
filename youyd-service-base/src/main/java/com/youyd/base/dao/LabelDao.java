package com.youyd.base.dao;

import com.youyd.base.pojo.Label;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @description: dao
 * @author: LGG
 * @create: 2018-09-26 16:21
 **/


public interface LabelDao {

	List findLabelByCondition(Map map);

	Label findLabelByPrimaryKey(String id);

	void deleteByPrimaryKey(List list);

	void insertLabel(Label label);

	void updateByPrimaryKeySelective(Label label);

}
