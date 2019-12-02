package com.ibole.article.dao.backstage;

import com.ibole.pojo.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 文章数据处理层
 **/


public interface ArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {

	@Modifying
	@Query("delete from Article where id in (:ids)")
	void deleteBatch(List<String> ids);

	/**
	 * 审核文章
	 *
	 * @param id
	 */
	@Modifying
	@Query("update Article set state='1' where id=:id")
	void examine(String id);

	/**
	 * 点赞
	 */
	@Modifying
	@Query("update Article a set upvote = upvote+1 where id=:id")
	void updateThumbUp(String id);
}
