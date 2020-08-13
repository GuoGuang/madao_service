package com.codeway.article.dao.blog;

import com.codeway.model.pojo.article.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApiArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {

	/**
	 * 获取随机六条文章作为推荐数据
	 */
	@Modifying
	@Query(value = "SELECT * FROM ar_article ORDER BY rand() LIMIT 6",
			nativeQuery = true)
	List<Article> findRelatedByRand();

//	@Query(value = "SELECT aa.*,ac.name,ac.id as cid FROM ar_article aa LEFT JOIN ar_category ac on aa.category_id = ac.id",
//					nativeQuery = true)
//	Page<Article> findArticlePage(Page page, QueryVO queryVO);

	/**
	 * 审核文章
	 */
//	void examine(String id);

	/**
	 * 点赞
	 */
	@Modifying
	@Query("update Article a set upvote = upvote+1 where id=:id")
	void updateUpVote(@Param("id") String id);

	@Modifying
	@Query("update Article a set upvote = upvote-1 where id=:id")
	void updateUnUpVote(@Param("id") String id);

	@Query(value = "SELECT * FROM ar_article WHERE id in (SELECT article_id FROM ar_article_tags WHERE tag_id = :tagId)", nativeQuery = true)
	Page<Article> findArticleByTagId(String tagId, Pageable pageable);
}
