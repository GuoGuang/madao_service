package com.madao.article.service.blog;

import com.madao.article.dao.backstage.ArticleDao;
import com.madao.article.dao.backstage.CommentDao;
import com.madao.article.mapper.CommentMapper;
import com.madao.model.dto.article.CommentDto;
import com.madao.model.pojo.article.Article;
import com.madao.model.pojo.article.Comment;
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
public class ApiCommentService {

    private final CommentDao commentDao;
    private final ArticleDao articleDao;
    private final CommentMapper commentMapper;

    public ApiCommentService(CommentDao commentDao, ArticleDao articleDao, CommentMapper commentMapper) {
        this.commentDao = commentDao;
        this.articleDao = articleDao;
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
     * @param commentId 评论表id
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

    public List<HashMap<Object, Object>> findMyComment(String userId) {

        List<Comment> myCurrentComment = commentDao.findByUserIdAndParentIdIs(userId, "");
        if (myCurrentComment.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> articleIds = myCurrentComment
                .stream()
                .map(Comment::getArticleId)
                .collect(Collectors.toList());
        List<Article> articles = articleDao.findAllById(articleIds);

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
                .collect(Collectors.toList());
    }
}
