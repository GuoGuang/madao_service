package com.youyd.tweets.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import java.util.Map;

/**
 * @description: 吐槽服务
 * @author: LGG
 * @create: 2018-09-27
 **/
@Service
public class TweetsService {

	@Autowired
	private TweetsDao tweetsDao;

	/**
	 * 按照条件查询全部标签
	 * @param map
	 * @param page
	 * @param size
	 * @return
	 */
	public Result findTweetsByCondition(Map map, Integer page, Integer size) {
		QueryWrapper queryWrapper = new QueryWrapper();
		List result = tweetsDao.selectList(queryWrapper);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
	}

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public Tweets findTweetsByPrimaryKey(String id) {
		return tweetsDao.selectById(id);
	}

	/**
	 * 发布吐槽（或吐槽评论）
	 * @param tweets
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
	 * @param
	 */
	public void updateByPrimaryKeyfindive(Tweets tweets) {
		tweetsDao.updateById(tweets);
	}

	/**
	 * 删除
	 * @param ids
	 */
	public void deleteById(String ids) {
		List<String> delIds = CodeCommonUtil.deletePart(ids);
		tweetsDao.deleteBatchIds(delIds);
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



