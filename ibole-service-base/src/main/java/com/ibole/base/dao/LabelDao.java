package com.ibole.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ibole.pojo.base.Label;

import java.util.List;
import java.util.Map;

/**
 * dao
 **/


public interface LabelDao  extends BaseMapper<Label> {

	List findLabelByCondition(Map map);

	Label findLabelByPrimaryKey(String id);

	void deleteByPrimaryKey(List list);

	void insertLabel(Label label);

	void updateByPrimaryKeySelective(Label label);

}
