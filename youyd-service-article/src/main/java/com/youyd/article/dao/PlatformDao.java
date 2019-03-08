package com.youyd.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youyd.article.pojo.Article;
import com.youyd.pojo.QueryVO;

/**
 * @description: 文章数据处理层
 * @author: LGG
 * @create: 2018-09-26 16:21
 **/


public interface PlatformDao extends BaseMapper<Article> {

	IPage<Article> findArticlePage(Page page, QueryVO queryVO);
}
