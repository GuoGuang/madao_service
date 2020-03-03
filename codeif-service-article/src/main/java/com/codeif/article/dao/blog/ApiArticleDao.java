package com.codeif.article.dao.blog;

import com.codeif.pojo.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 文章数据处理层
 **/


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
