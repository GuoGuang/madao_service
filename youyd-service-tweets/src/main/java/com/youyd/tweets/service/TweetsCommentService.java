package com.youyd.tweets.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.youyd.pojo.QueryVO;
import com.youyd.tweets.dao.TweetsCommentDao;
import com.youyd.tweets.pojo.TweetsComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 吐槽评论
 * @author: LGG
 * @create: 2018-09-27
 **/
@Service
public class TweetsCommentService {

	private final TweetsCommentDao tweetsCommentCommentDao;

	@Autowired
	public TweetsCommentService(TweetsCommentDao tweetsCommentCommentDao) {
		this.tweetsCommentCommentDao = tweetsCommentCommentDao;
	}

	/**
	 * 按照条件查询全部标签
	 * @return IPage
	 */
	public IPage<TweetsComment> findTweetsCommentByCondition(TweetsComment tweetsComment, QueryVO queryVO){
		Page<TweetsComment> pr = new Page<>(queryVO.getPage(),queryVO.getLimit());
		QueryWrapper<TweetsComment> queryWrapper = new QueryWrapper<>();
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
	public void insertTweetsComment(TweetsComment tweetsComment){
		tweetsComment.setThumbUpCount(0L);
		tweetsComment.setReplyCount(0L);
		tweetsComment.setIsVisible(1);
		// 如果存在上级ID,则是上级评论的子评论,给父评论回复数加一
		/*if(StringUtils.isNotBlank(tweetsComment.getParentId())){
			HashMap<Object, Object> map = new HashMap<>();
			map.put("_count","reply_count"); // 回复数 字段
			map.put("id",tweetsComment.getParentId());
			tweetsCommentCommentDao.updateCountByPrimaryKey(map);
		}*/
		tweetsCommentCommentDao.insert(tweetsComment);
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
	 * @param commentIds
	 */
	public boolean deleteByIds(List commentIds) {
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



