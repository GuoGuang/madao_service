package com.ibole.tweets.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ibole.pojo.tweets.Tweets;

/**
 * dao
 **/

public interface TweetsDao extends BaseMapper<Tweets> {
	/**
	 * 根据上级ID查询吐槽列表（分页）
	 * @param parentId
	 * @return
	 */
	Tweets findTweetsByParentid(String parentId);

	/**
	 * 更新 点赞数,回复数,分享数或者评论数 的通用更新方法
	 */
	void updateTweetsStatus(Tweets tweets);
}
