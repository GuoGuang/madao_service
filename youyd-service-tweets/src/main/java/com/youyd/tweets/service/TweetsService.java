package com.youyd.tweets.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.Result;
import com.youyd.tweets.dao.TweetsDao;
import com.youyd.tweets.pojo.Tweets;
import com.youyd.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 吐槽服务
 * @author: LGG
 * @create: 2018-09-27
 **/
@Service
public class TweetsService {

	private final TweetsDao tweetsDao;

	@Autowired
	public TweetsService(TweetsDao tweetsDao) {
		this.tweetsDao = tweetsDao;
	}

	/**
	 * 按照条件查询
	 * @return IPage
	 */
	public IPage<Tweets> findTweetsByCondition(Tweets tweets, QueryVO queryVO){
		Page<Tweets> pr = new Page<>(queryVO.getPage(),queryVO.getLimit());
		QueryWrapper<Tweets> queryWrapper = new QueryWrapper<>();
		return tweetsDao.selectPage(pr, queryWrapper);
	}


	/**
	 * 根据ID查询
	 * @param tweetsId 吐槽id
	 * @return Tweets
	 */
	public Tweets findTweetsByPrimaryKey(String tweetsId) {
		return tweetsDao.selectById(tweetsId);
	}

	/**
	 * 发布吐槽
	 * @param tweets 吐槽实体
	 */
	public void insertTweets(Tweets tweets){
		tweets.setVisitsCount(0L);
		tweets.setShareCount(0L);
		tweets.setThumbUpCount(0L);
		tweets.setReplyCount(0L);
		tweets.setIsVisible(1);
		tweetsDao.insert(tweets);
	}

	/**
	 * 修改
	 * @param tweets 吐槽实体
	 */
	public boolean updateByTweetsSelective(Tweets tweets) {
		int i = tweetsDao.updateById(tweets);
		return SqlHelper.retBool(i);
	}

	/**
	 * 删除
	 * @param ids
	 */
	public boolean deleteByTweetsId(List tweetsId) {
		int i = tweetsDao.deleteBatchIds(tweetsId);
		return SqlHelper.retBool(i);
	}

	/**
	 * 根据上级ID查询吐槽列表
	 * @param parentId : 上级ID
	 * @param page
	 * @param size
	 * @return Result
	 */
	public Result findTweetsByParentid(String parentId){
		Tweets result = tweetsDao.findTweetsByParentid(parentId);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
	}

	/**
	 * 更新吐槽表字段状态
	 */
	public void updateTweetsStatus(Tweets tweets){
		tweetsDao.updateTweetsStatus(tweets);
	}
}



