package com.ibole.article.service.backstage;

import com.ibole.article.dao.backstage.CommentDao;
import com.ibole.exception.custom.ResourceNotFoundException;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.article.Comment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * 文章的评论服务
 **/

@Service
public class CommentService {

	private final CommentDao commentDao;

	@Autowired
	public CommentService(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	/**
	 * 查询全部列表
	 *
	 * @param comment 实体
	 * @param queryVO 查询条件
	 * @return IPage<Comment>
	 */
	public Page<Comment> findCommentByCondition(Comment comment, QueryVO queryVO) {
		Specification<Comment> condition = (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(comment.getContent())) {
				predicates.add(builder.like(root.get("content"), "%" + comment.getContent() + "%"));
			}
			Predicate[] ps = new Predicate[predicates.size()];
			query.where(builder.and(predicates.toArray(ps)));
			query.orderBy(builder.desc(root.get("createAt").as(Long.class)));
			return null;
		};
		return commentDao.findAll(condition, queryVO.getPageable());

	}

	/**
	 * 根据评论表ID查询评论
	 *
	 * @param commentId 评论表ID
	 * @return Comment
	 */
	public Comment findCommentByPrimaryKey(String commentId) {
        return commentDao.findById(commentId).orElseThrow(ResourceNotFoundException::new);
    }

	public void saveOrUpdate(Comment comment) {
		commentDao.save(comment);
	}

	public void deleteCommentByIds(List<String> commentIds) {
		commentDao.deleteBatch(commentIds);
	}

}
