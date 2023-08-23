package com.madao.article.dao.backstage;

import com.madao.model.entity.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public interface ArticleDao extends JpaRepository<Article, String>,
		JpaSpecificationExecutor<Article>, QuerydslPredicateExecutor<Article> {

	@Modifying
	@Query("delete from Article where id in (:ids)")
	void deleteBatch(@Param("ids") List<String> ids);

	/**
	 * 审核文章
	 *
	 * @param id
	 */
	@Modifying
	@Query(nativeQuery = true,value = "update ar_article set reviewState = 1 where id=:id")
	void examine(@Param("id") String id);

	List<Article> findByCategoryIdIn(List<String> ids);
}
