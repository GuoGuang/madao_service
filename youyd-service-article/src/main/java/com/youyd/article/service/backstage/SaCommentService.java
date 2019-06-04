package com.youyd.article.service.backstage;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youyd.article.dao.backstage.SaCommentDao;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.article.Comment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章的评论服务
 * @author : LGG
 * @create : 2018-10-13 16:39
 **/

@Service
public class SaCommentService {

	private final SaCommentDao saCommentDao;

	@Autowired
	public SaCommentService(SaCommentDao saCommentDao) {
		this.saCommentDao = saCommentDao;
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
		queryWrapper.orderByDesc(Comment::getCreateAt);
		return saCommentDao.selectPage(pr, queryWrapper);
	}

	/**
	 * 根据评论表ID查询评论
	 * @param commentId 评论表ID
	 * @return Comment
	 */
	public Comment findCommentByPrimaryKey(String commentId) {
		return saCommentDao.selectById(commentId);
	}

	/**
	 * 增加
	 */
	public void insertComment(Comment comment) {
		saCommentDao.insert(comment);
	}
	/**
	 * 修改
	 */
	public void updateByCommentSelective(Comment comment) {
		saCommentDao.updateById(comment);
	}

	/**
	 * 删除
	 */
	public void deleteCommentByIds(List<String> commentIds) {
		saCommentDao.deleteBatchIds(commentIds);
	}

}
