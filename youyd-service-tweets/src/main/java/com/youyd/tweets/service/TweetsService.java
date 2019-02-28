package com.youyd.tweets.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.tweets.dao.TweetsDao;
import com.youyd.tweets.pojo.Tweets;
import com.youyd.pojo.Result;
import com.youyd.utils.CodeCommonUtil;
import com.youyd.utils.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
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
	 * 按照条件查询全部标签
	 * @return IPage
	 */
	public IPage<Tweets> findTweetsByCondition(Tweets tweets) {
		Page<Tweets> pr = new Page<>(tweets.getPage(),tweets.getLimit());
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
	 * 发布吐槽（或吐槽评论）
	 * @param tweets 吐槽实体
	 */
	public void insertTweets(Tweets tweets){
		tweets.setPublishTime(new Date());//发布日期
		tweets.setVisitsCount(0L);//浏览量
		tweets.setShareCount(0L);//分享数
		tweets.setThumbUpCount(0L);//点赞数
		tweets.setReplyCount(0L);//回复数
		tweets.setIsVisible(1);//是否可见
		// 如果存在上级ID,则是上级评论的子评论,给父评论回复数加一
		if(StringUtils.isNotBlank(tweets.getParentId())){
			HashMap<Object, Object> map = new HashMap<>();
			map.put("_count","reply_count"); // 回复数 字段
			map.put("id",tweets.getParentId());
			tweetsDao.updateCountByPrimaryKey(map);
		}
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
	public Result findTweetsByParentid(String parentId, Integer page, Integer size){
		Tweets result = tweetsDao.findTweetsByParentid(parentId);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
	}

	/**
	 * 点赞
	 * @param id:被点赞的吐槽id
	 */
	public void updateThumbUp(String id){
		HashMap<Object, Object> map = new HashMap<>();
		map.put("_count","thumb_up_count"); // 点赞 字段
		map.put("id",id);
		tweetsDao.updateCountByPrimaryKey(map);
	}
}



