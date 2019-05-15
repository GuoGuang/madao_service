package com.youyd.article.dao.blog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.article.Article;

/**
 * @description: 文章数据处理层
 * @author: LGG
 * @create: 2018-09-26 16:21
 **/


public interface ArticleDao extends BaseMapper<Article> {

	IPage<Article> findArticlePage(Page page, QueryVO queryVO);

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
