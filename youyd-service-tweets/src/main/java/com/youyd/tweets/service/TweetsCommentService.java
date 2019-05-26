package com.youyd.tweets.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.tweets.Tweets;
import com.youyd.pojo.tweets.TweetsComment;
import com.youyd.tweets.dao.TweetsCommentDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 吐槽评论
 * @author: LGG
 * @create: 2018-09-27
 **/
@Service
public class TweetsCommentService {

	private final TweetsCommentDao tweetsCommentCommentDao;
	private final TweetsService tweetsService;

	@Autowired
	public TweetsCommentService(TweetsCommentDao tweetsCommentCommentDao,TweetsService tweetsService) {
		this.tweetsCommentCommentDao = tweetsCommentCommentDao;
		this.tweetsService = tweetsService;
	}

	/**
	 * 按照条件查询全部评论
	 * @return IPage
	 */
	public IPage<TweetsComment> findTweetsCommentByCondition(TweetsComment tweetsComment, QueryVO queryVO){
		Page<TweetsComment> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());
		LambdaQueryWrapper<TweetsComment> queryWrapper = new LambdaQueryWrapper<>();
		if (StringUtils.isNotEmpty(tweetsComment.getNickName())) {
			queryWrapper.like(TweetsComment::getNickName, tweetsComment.getNickName());
		}
		return tweetsCommentCommentDao.selectPage(pr, queryWrapper);
	}


	/**
	 * 根据ID查询
	 * @param tweetsCommentId 吐槽id
	 * @return TweetsComment
	 */
	public TweetsComment findTweetsCommentByPrimaryKey(String tweetsCommentId) {
		return tweetsCommentCommentDao.selectById(tweetsCommentId);
	}

	/**
	 * 发布吐槽评论
	 * @param tweetsComment 吐槽实体
	 */
	public void insertTweetsComment(TweetsComment tweetsComment,String tweetsId){
		tweetsComment.setLikeNum(0L);
		tweetsComment.setIsVisible(1);
		tweetsCommentCommentDao.insert(tweetsComment);

		Tweets tweets = new Tweets();
		tweets.setId(tweetsId);
		tweetsService.updateTweetsStatus(tweets);

		// 如果存在上级ID,则是上级评论的子评论,给父评论回复数加一
		/*if(StringUtils.isNotBlank(tweetsComment.getParentId())){
			HashMap<Object, Object> map = new HashMap<>();
			map.put("_count","reply_count"); // 回复数 字段
			map.put("id",tweetsComment.getParentId());
			tweetsCommentCommentDao.updateCountByPrimaryKey(map);
		}*/
	}

	/**
	 * 修改
	 * @param tweetsComment 评论实体
	 */
	public boolean updateByTweetsCommentSelective(TweetsComment tweetsComment) {
		int i = tweetsCommentCommentDao.updateById(tweetsComment);
		return SqlHelper.retBool(i);
	}

	/**
	 * 删除
	 * @param commentIds 要删除的评论
	 */
	public boolean deleteByIds(List<String> commentIds) {
		int i = tweetsCommentCommentDao.deleteBatchIds(commentIds);
		return SqlHelper.retBool(i);
	}

	/**
	 * 根据上级ID查询吐槽列表
	 * @param parentId : 上级ID
	 * @param page
	 * @param size
	 * @return Result
	 */
	/*public Result findTweetsCommentByParentid(String parentId){
		TtweetsCommentComment result = tweetsCommentCommentDao.findTtweetsCommentCommentByParentid(parentId);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
	}*/

}



