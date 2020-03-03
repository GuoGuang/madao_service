package com.codeif.article.service.backstage;

import com.codeif.article.dao.backstage.CommentDao;
import com.codeif.exception.custom.ResourceNotFoundException;
import com.codeif.pojo.QueryVO;
import com.codeif.pojo.article.Comment;
import com.codeif.pojo.article.QComment;
import com.codeif.utils.QuerydslUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章的评论服务
 **/

@Service
public class CommentService {

    private final CommentDao commentDao;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Autowired
    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    /**
     * 查询全部列表
     *
     * @param comment 实体
     * @param queryVO 查询条件
     */
    public QueryResults<Comment> findCommentByCondition(Comment comment, QueryVO queryVO) {

        QComment qComment = QComment.comment;
        com.querydsl.core.types.Predicate predicate = null;
        OrderSpecifier<?> sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qComment);
        if (StringUtils.isNotEmpty(comment.getContent())) {
            predicate = ExpressionUtils.and(predicate, qComment.content.like(comment.getContent()));
        }
        if (StringUtils.isNotEmpty(queryVO.getFieldSort())) {
            sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qComment, queryVO.getFieldSort());
        }
        QueryResults<Comment> queryResults = jpaQueryFactory
                .selectFrom(qComment)
                .where(predicate)
                .offset(queryVO.getPageNum())
                .limit(queryVO.getPageSize())
                .orderBy(sortedColumn)
                .fetchResults();
        return queryResults;
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
