package com.ibole.article.dao.blog;

import com.ibole.pojo.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 文章数据处理层
 **/


public interface ApiArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {

//	@Query(value = "SELECT aa.*,ac.name,ac.id as cid FROM ar_article aa LEFT JOIN ar_category ac on aa.category_id = ac.id",
//					nativeQuery = true)
//	Page<Article> findArticlePage(Page page, QueryVO queryVO);

	/**
	 * 审核文章
	 *
	 * @param id
	 */
//	void examine(String id);

	/**
	 * 点赞
	 *
	 * @param id
	 * @return
	 */
//	int updateThumbUp(String id);
}
