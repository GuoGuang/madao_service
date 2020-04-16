package com.codeway.article.service.backstage;

import com.codeway.article.dao.backstage.CommentDao;
import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.pojo.QueryVO;
import com.codeway.pojo.article.Comment;
import com.codeway.pojo.article.QComment;
import com.codeway.pojo.user.Resource;
import com.codeway.utils.QuerydslUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

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
     * @param comment 查询实体
     */
    public Page<Comment> findCommentByCondition(Comment comment, Pageable pageable) {
        Specification<Comment> condition = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotEmpty(comment.getContent())) {
                predicates.add(builder.like(root.get("content"), "%" + comment.getContent() + "%"));
            }
            return query.where(predicates.toArray(new Predicate[0])).getRestriction();
        };
        return commentDao.findAll(condition,pageable);
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
