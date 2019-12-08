package com.ibole.article.dao.backstage;

import com.ibole.pojo.article.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 文章评论
 **/


public interface CommentDao extends JpaRepository<Comment, String>, JpaSpecificationExecutor<Comment> {

    @Modifying
    @Query("delete from Dict where id in (:ids)")
    void deleteBatch(@Param("ids") List<String> ids);

}
