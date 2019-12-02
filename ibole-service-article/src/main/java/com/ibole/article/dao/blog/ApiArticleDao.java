package com.ibole.article.dao.blog;

import com.ibole.pojo.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 文章数据处理层
 **/


public interface ApiArticleDao extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {

//	IPage<Article> findArticlePage(Page page, QueryVO queryVO);

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
