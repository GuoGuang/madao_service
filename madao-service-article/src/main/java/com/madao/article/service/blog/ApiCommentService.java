package com.madao.article.service.blog;

import com.madao.article.mapper.CommentMapper;
import com.madao.article.repository.backstage.ArticleRepository;
import com.madao.article.repository.backstage.CommentRepository;
import com.madao.model.dto.article.CommentDto;
import com.madao.model.entity.article.Article;
import com.madao.model.entity.article.Comment;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Service
@AllArgsConstructor
public class ApiCommentService {

	private final CommentRepository commentRepository;
	private final ArticleRepository articleRepository;
	private final CommentMapper commentMapper;

	public List<CommentDto> findCommentByCondition(String articleId) {
		List<Comment> content = commentRepository.findByArticleIdOrderByCreateAtDesc(articleId);
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
				).toList();
	}

	/**
	 * 非幂等
	 * TODO 使用点赞表解决
	 *
	 * @param commentId 评论表id
	 */
	public void upVote(String commentId) {
		commentRepository.updateUpVote(commentId);
	}

	public void unUpVote(String commentId) {
		commentRepository.updateUnUpVote(commentId);
	}


	public void addComment(Comment comment) {
		if (StringUtils.isBlank(comment.getId())) {
			comment.setUpvote(0);
		}
		commentRepository.save(comment);
	}

	public List<HashMap<Object, Object>> findMyComment(String userId) {

		List<Comment> myCurrentComment = commentRepository.findByUserIdAndParentIdIs(userId, "");
		if (myCurrentComment.isEmpty()) {
			return Collections.emptyList();
		}

		List<String> articleIds = myCurrentComment
				.stream()
				.map(Comment::getArticleId)
				.toList();
		List<Article> articles = articleRepository.findAllById(articleIds);

		return myCurrentComment.stream().flatMap(comment -> articles.stream()
						.filter(article -> comment.getArticleId().equals(article.getId()))
						.map(article ->
								{
									HashMap<Object, Object> map = new HashMap<>();
									map.put("articleId", article.getId());
									map.put("articleTitle", article.getTitle());
									map.put("commentContent", comment.getContent());
									map.put("commentCreateAt", comment.getCreateAt());
									return map;
								}
						))
				.toList();
	}
}
