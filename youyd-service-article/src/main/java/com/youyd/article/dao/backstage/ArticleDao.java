package com.youyd.article.dao.backstage;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youyd.pojo.article.Article;

/**
 * @description: 文章数据处理层
 * @author LGG
 * @create 2018-09-26 16:21
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
