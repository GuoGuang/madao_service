package com.youyd.article.service;

import com.youyd.article.pojo.Comment;

import java.util.List;

/**
 * @description: 文章的评论服务
 * @author: LGG
 * @create: 2018-10-13 16:39
 **/
public interface CommentService {

	List<Comment> findCommentByCondition();

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	Comment findCommentByPrimaryKey(String id);

	/**
	 * 增加
	 * @param comment
	 */
	void insertComment(Comment comment);

	/**
	 * 删除
	 * @param commentIds
	 */
	void deleteByIds(List commentIds);
}
