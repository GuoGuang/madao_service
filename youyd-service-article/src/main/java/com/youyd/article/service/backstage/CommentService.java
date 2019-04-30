package com.youyd.article.service.backstage;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youyd.article.dao.backstage.CommentDao;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.article.Comment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 文章的评论服务
 * @author: LGG
 * @create: 2018-10-13 16:39
 **/

@Service
public class CommentService{

	private final CommentDao commentDao;

	@Autowired
	public CommentService(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	/**
	 * 查询全部列表
	 * @param comment 实体
	 * @param queryVO 查询条件
	 * @return IPage<Comment>
	 *
	 */
	public IPage<Comment> findCommentByCondition(Comment comment, QueryVO queryVO) {
		Page<Comment> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());

		LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

		if (StringUtils.isNotEmpty(comment.getContent())){
			queryWrapper.eq(Comment::getContent,comment.getContent());
		}

		return commentDao.selectPage(pr, queryWrapper);
	}

	/**
	 * 根据评论表ID查询评论
	 * @param id 评论表ID
	 * @return Comment
	 */
	public Comment findCommentByPrimaryKey(String commentId) {
		return commentDao.selectById(commentId);
	}

	/**
	 * 增加
	 */
	public void insertComment(Comment comment) {
		commentDao.insert(comment);
	}
	/**
	 * 修改
	 */
	public void updateByCommentSelective(Comment comment) {
		commentDao.updateById(comment);
	}

	/**
	 * 删除
	 */
	public void deleteCommentByIds(List<String> commentIds) {
		commentDao.deleteBatchIds(commentIds);
	}

}
