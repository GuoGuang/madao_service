package com.youyd.article.service.impl;

import com.youyd.article.dao.CommentDao;
import com.youyd.article.pojo.Comment;
import com.youyd.article.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 文章的评论服务
 * @author: LGG
 * @create: 2018-10-13 16:39
 **/

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	/**
	 * 查询全部列表
	 * @return
	 */
	@Override
	public List<Comment> findCommentByCondition() {
		return commentDao.selectList(null);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	@Override
	public Comment findCommentByPrimaryKey(String id) {
		return commentDao.selectById(id);
	}

	/**
	 * 增加
	 * @param comment
	 */
	@Override
	public void insertComment(Comment comment) {
		commentDao.insert(comment);
	}

	/**
	 * 删除
	 * @param commentIds
	 */
	@Override
	public void deleteByIds(List commentIds) {
		commentDao.deleteBatchIds(commentIds);
	}

}
