package com.youyd.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youyd.pojo.base.Label;

import java.util.List;
import java.util.Map;

/**
 * @description dao
 * @author LGG
 * @create 2018-09-26 16:21
 **/


public interface LabelDao  extends BaseMapper<Label> {

	List findLabelByCondition(Map map);

	Label findLabelByPrimaryKey(String id);

	void deleteByPrimaryKey(List list);

	void insertLabel(Label label);

	void updateByPrimaryKeySelective(Label label);

}
