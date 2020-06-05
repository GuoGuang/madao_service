package com.codeway.article.service.backstage;

import com.codeway.article.dao.backstage.CommentDao;
import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.pojo.article.Comment;
import com.codeway.utils.BeanUtil;
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

    public Comment findCommentByPrimaryKey(String commentId) {
        return commentDao.findById(commentId).orElseThrow(ResourceNotFoundException::new);
    }

    public void saveOrUpdate(Comment comment) {
	    if (StringUtils.isNotBlank(comment.getId())){
		    Comment tempComment = commentDao.findById(comment.getId()).orElseThrow(ResourceNotFoundException::new);
		    BeanUtil.copyProperties(tempComment, comment);
	    }
        commentDao.save(comment);
    }

    public void deleteCommentByIds(List<String> commentIds) {
        commentDao.deleteBatch(commentIds);
    }

}
