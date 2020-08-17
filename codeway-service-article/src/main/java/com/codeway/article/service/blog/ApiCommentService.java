package com.codeway.article.service.blog;

import com.codeway.article.dao.backstage.CommentDao;
import com.codeway.article.mapper.CommentMapper;
import com.codeway.model.dto.article.CommentDto;
import com.codeway.model.pojo.article.Comment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ApiCommentService {

	private final CommentDao commentDao;
	private final CommentMapper commentMapper;


	public ApiCommentService(CommentDao commentDao, CommentMapper commentMapper) {
		this.commentDao = commentDao;
		this.commentMapper = commentMapper;
	}

	public List<CommentDto> findCommentByCondition(String articleId) {
		List<Comment> content = commentDao.findByArticleIdOrderByCreateAtDesc(articleId);
		List<CommentDto> commentDto = commentMapper.toDto(content);

		Map<String, List<CommentDto>> subComment = commentDto.stream()
				.filter(o -> StringUtils.isNotEmpty(o.getParentId()))
				.collect(Collectors.groupingBy(CommentDto::getParentId));

		return commentDto.stream()
				.filter(o -> StringUtils.isEmpty(o.getParentId()))
				.map(cm -> {
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
	 *
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
