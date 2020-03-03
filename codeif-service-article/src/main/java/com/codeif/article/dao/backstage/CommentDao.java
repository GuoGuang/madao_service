package com.codeif.article.dao.backstage;

import com.codeif.pojo.article.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 文章评论
 **/


public interface CommentDao extends JpaRepository<Comment, String>,
        JpaSpecificationExecutor<Comment>, QuerydslPredicateExecutor<Comment> {
    @Modifying
    @Query("delete from Dict where id in (:ids)")
    void deleteBatch(@Param("ids") List<String> ids);

}
