package com.codeway.article.dao.backstage;

import com.codeway.model.pojo.article.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, String>,
        JpaSpecificationExecutor<Comment>, QuerydslPredicateExecutor<Comment> {

	@Modifying
	@Query("delete from Dict where id in (:ids)")
	void deleteBatch(@Param("ids") List<String> ids);

	@Modifying
	@Query("update Comment set upvote = upvote+1 where id=:commentId")
	void updateUpVote(@Param("commentId") String commentId);

	@Modifying
	@Query("update Comment set upvote = upvote-1 where id=:commentId")
	void updateUnUpVote(@Param("commentId") String commentId);

	List<Comment> findByArticleIdOrderByCreateAtDesc(String articleId);

	List<Comment> findByArticleIdIn(List<String> commentDao);

	List<Comment> findByUserIdAndParentIdIs(String userId, String parentId);
}
