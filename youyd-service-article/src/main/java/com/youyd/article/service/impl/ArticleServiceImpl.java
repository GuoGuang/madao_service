package com.youyd.article.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youyd.article.dao.ArticleDao;
import com.youyd.article.pojo.Article;
import com.youyd.article.service.ArticleService;
import com.youyd.cache.constant.RedisConstant;
import com.youyd.cache.redis.RedisService;
import com.youyd.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章板块:文章服务
 */

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;

	@Autowired
	private RedisService redisService;

	/**
	 * 查询全部列表
	 * @return
	 */
	@Override
	public IPage<Article> findArticleByCondition(Article article) {
		Page<Article> pr = new Page<>(article.getPage(),article.getLimit());
		QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
		IPage<Article> articleIPage = articleDao.selectPage(pr, queryWrapper);
		return articleIPage;
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	@Override
	public Article findArticleByPrimaryKey(String id) {
		Object mapJson = redisService.get(RedisConstant.REDIS_KEY_ARTICLE + id);
		Article article = JsonUtil.mapToPojo(mapJson, Article.class);
		// 如果缓存没有则到数据库查询并放入缓存,有效期一天
		if(article==null) {
			article = articleDao.selectById(id);
			redisService.set(RedisConstant.REDIS_KEY_ARTICLE+ id, article,RedisConstant.REDIS_TIME_DAY);
		}
		return article;
	}

	/**
	 * 增加
	 * @param article
	 */
	@Override
	public void insertArticle(Article article) {
		articleDao.insert(article);
	}

	/**
	 * 修改
	 * @param article
	 */
	@Override
	public void updateByPrimaryKeySelective(Article article) {
		redisService.del( "article_" + article.getId() );//删除缓存
		articleDao.updateById(article);
	}

	/**
	 * 删除
	 * @param articleIds:文章id集合
	 */
	@Override
	public void deleteByIds(List articleIds) {
		redisService.del( "article_" + articleIds );//删除缓存
		articleDao.deleteBatchIds(articleIds);
	}

	/**
	 * 审核文章
	 * @param id
	 */
	@Override
	public void examine(String id){
		articleDao.examine(id);
	}

	/**
	 * 点赞
	 * @param id 文章ID
	 * @return
	 */
	@Override
	public int updateThumbup(String id){
		return articleDao.updateThumbup(id);
	}

}
