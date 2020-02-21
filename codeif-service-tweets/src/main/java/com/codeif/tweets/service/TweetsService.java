package com.codeif.tweets.service;

import com.codeif.enums.StatusEnum;
import com.codeif.pojo.QueryVO;
import com.codeif.pojo.Result;
import com.codeif.pojo.tweets.Tweets;
import com.codeif.tweets.dao.TweetsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 吐槽服务
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
     *
     * @return IPage
     */
    public List<Tweets> findTweetsByCondition(Tweets tweets, QueryVO queryVO) {
//		Page<Tweets> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());
//		LambdaQueryWrapper<Tweets> queryWrapper = new LambdaQueryWrapper<>();
//		if (StringUtils.isNotEmpty(tweets.getNickName())) {
//			queryWrapper.like(Tweets::getNickName, tweets.getNickName());
//		}
//		queryWrapper.orderByDesc(Tweets::getCreateAt);
//		return tweetsDao.selectPage(pr, queryWrapper);

        return null;
    }


	/**
	 * 根据ID查询
	 * @param tweetsId 吐槽id
	 * @return Tweets
	 */
	public Tweets findTweetsByPrimaryKey(String tweetsId) {
//		return tweetsDao.selectById(tweetsId);

        return null;
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
//		tweetsDao.insert(tweets);
	}

	/**
	 * 修改
	 * @param tweets 吐槽实体
	 */
	public boolean updateByTweetsSelective(Tweets tweets) {
//		int i = tweetsDao.updateById(tweets);
//		return SqlHelper.retBool(i);
        return false;
	}

	/**
	 * 删除
	 * @param tweetsId 要删除的id
	 */
	public boolean deleteByTweetsId(List<String> tweetsId) {
//		int i = tweetsDao.deleteBatchIds(tweetsId);
//		return SqlHelper.retBool(i);

        return false;
	}

	/**
	 * 根据上级ID查询吐槽列表
	 * @param parentId : 上级ID
	 * @param page
	 * @param size
	 * @return Result
	 */
	public Result findTweetsByParentid(String parentId) {
//		Tweets result = tweetsDao.findTweetsByParentid(parentId);
        return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),null);
	}

    /**
     * 更新吐槽表字段状态
     * update tc_tweets
     * <set>
     * <if test="visitsCount != null ">
     * visits_count = visits_count + 1
     * </if>
     * <if test="thumbUpCount != null ">
     * thumb_up_count = thumbUpCount + 1
     * </if>
     * <if test="shareCount != null ">
     * share_count = shareCount + 1
     * </if>
     * <if test="replyCount != null ">
     * reply_count = replyCount + 1
     * </if>
     * </set>
     * where id = #{id}
     */
	public void updateTweetsStatus(Tweets tweets) {
		tweetsDao.updateTweetsStatus(tweets);
	}
}



