package com.codeway.article.service.blog;

import com.codeway.article.dao.backstage.CommentDao;
import com.codeway.pojo.article.Comment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ApiCommentService {

	private final CommentDao commentDao;

	@Autowired
	public ApiCommentService(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public List<Comment> findCommentByCondition(String articleId) {
		List<Comment> content = commentDao.findByArticleIdOrderByCreateAtDesc(articleId);
		Map<String, List<Comment>> subComment = content.stream().filter(o -> StringUtils.isNotEmpty(o.getParentId())).collect(Collectors.groupingBy(Comment::getParentId));
		return content.stream().filter(o -> StringUtils.isEmpty(o.getParentId())).map(cm -> {
					if (subComment.containsKey(cm.getId())) {
						cm.setReply(subComment.get(cm.getId()));
					}
					return cm;
				}
		).collect(Collectors.toList());
	}

	/**
	 * 非幂等
	 * TODO 使用点赞表解决
	 * @param commentId
	 */
	public void upVote(String commentId) {
		commentDao.updateUpVote(commentId);
	}
	public void unUpVote(String commentId) {
		commentDao.updateUnUpVote(commentId);
	}


	public void addComment(Comment comment) {
		if (StringUtils.isBlank(comment.getId())) {
			comment.setUpvote(0);
		}
		commentDao.save(comment);
	}
}
