package com.ibole.article.dao.backstage;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ibole.pojo.article.Article;

/**
 * 文章数据处理层
 **/


public interface ArticleDao extends BaseMapper<Article> {

	/**
	 * 审核文章
	 *
	 * @param id
	 */
	void examine(String id);

	/**
	 * 点赞
	 *
	 * @param id
	 * @return
	 */
	int updateThumbUp(String id);
}
