package com.youyd.tweets.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youyd.tweets.pojo.Spit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @description: dao
 * @author: LGG
 * @create: 2018-09-26 16:21
 **/

public interface SpitDao extends BaseMapper<Spit> {
	/**
	 * 根据上级ID查询吐槽列表（分页）
	 * @param parentId
	 * @return
	 */
	Spit findSpitByParentid(String parentId);

	/**
	 * 更新 点赞数,回复数,分享数或者评论数 的通用更新方法
	 */
	void updateCountByPrimaryKey(Map paramMap);
}
