package com.youyd.tweets.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.enums.StatusEnum;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.Result;
import com.youyd.pojo.tweets.Tweets;
import com.youyd.tweets.dao.TweetsDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 吐槽服务
 * @author LGG
 * @create 2018-09-27
 **/

@Service
public class TweetsService {

	private final TweetsDao tweetsDao;

	@Autowired
	public TweetsService(TweetsDao tweetsDao) {
		this.tweetsDao = tweetsDao;
	}

	/**
	 * 按照条件查询吐槽内容
	 * @return IPage
	 */
	public IPage<Tweets> findTweetsByCondition(Tweets tweets, QueryVO queryVO){
		Page<Tweets> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());
		LambdaQueryWrapper<Tweets> queryWrapper = new LambdaQueryWrapper<>();
		if (StringUtils.isNotEmpty(tweets.getNickName())) {
			queryWrapper.like(Tweets::getNickName, tweets.getNickName());
		}
		queryWrapper.orderByDesc(Tweets::getCreateAt);
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
	 * @param tweetsId 要删除的id
	 */
	public boolean deleteByTweetsId(List<String> tweetsId) {
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
		return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),result);
	}

	/**
	 * 更新吐槽表字段状态
	 */
	public void updateTweetsStatus(Tweets tweets){
		tweetsDao.updateTweetsStatus(tweets);
	}
}



