package com.youyd.article.service.backstage;

import com.youyd.article.dao.backstage.CommentDao;
import com.youyd.article.pojo.Comment;
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

	@Autowired
	private CommentDao commentDao;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Comment> findCommentByCondition() {
		return commentDao.selectList(null);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Comment findCommentByPrimaryKey(String id) {
		return commentDao.selectById(id);
	}

	/**
	 * 增加
	 * @param comment
	 */
	public void insertComment(Comment comment) {
		commentDao.insert(comment);
	}
	/**
	 * 修改
	 * @param comment
	 */
	public void updateByCommentSelective(Comment comment) {
		commentDao.updateById(comment);
	}


	/**
	 * 删除
	 * @param commentIds
	 */
	public void deleteByIds(List commentIds) {
		commentDao.deleteBatchIds(commentIds);
	}

}
