package com.ibole.article.service.blog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibole.article.dao.blog.ApiArticleDao;
import com.ibole.cache.constant.RedisConstant;
import com.ibole.cache.redis.RedisService;
import com.ibole.constant.CommonConst;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.article.Article;
import com.ibole.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章板块:文章服务
 **/
@Service
public class ApiArticleService{

	private final ApiArticleDao articleDao;

	private final RedisService redisService;

	@Autowired
	public ApiArticleService(ApiArticleDao articleDao, RedisService redisService) {
		this.articleDao = articleDao;
		this.redisService = redisService;
	}

	/**
	 * 查询文章
	 * @return IPage<Article>
	 */
	public IPage<Article> findArticleByCondition(Article article, QueryVO queryVO ){
		Page<Article> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());
		LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
		if (StringUtils.isNotEmpty(article.getTitle())) {
			queryWrapper.like(Article::getTitle, article.getTitle());
		}
		if (StringUtils.isNotEmpty(article.getDescription())) {
			queryWrapper.like(Article::getDescription, article.getDescription());
		}
		return articleDao.selectPage(pr, queryWrapper);
	}

	/**
	 * 根据ID查询实体
	 * @param articleId 文章id
	 * @return Article
	 */
	public Article findArticleById(String articleId) {
		Object mapJson = redisService.get(RedisConstant.REDIS_KEY_ARTICLE + articleId);
		Article article = JsonUtil.jsonToPojo(mapJson.toString(), Article.class);
		// 如果缓存没有则到数据库查询并放入缓存,有效期一天
		if(article==null) {
			article = articleDao.selectById(articleId);
			redisService.set(RedisConstant.REDIS_KEY_ARTICLE+ articleId, article, CommonConst.TIME_OUT_DAY);
		}
		return article;
	}

	/**
	 * 增加
	 * @param article 实体
	 */
	public void insertArticle(Article article) {
		articleDao.insert(article);
	}

	/**
	 * 修改
	 * @param article 实体
	 */
	public void updateByPrimaryKeySelective(Article article) {
		redisService.del( "article_" + article.getId());
		articleDao.updateById(article);
	}

	/**
	 * 删除
	 * @param articleIds:文章id集合
	 */
	public void deleteArticleByIds(List<String> articleIds) {
		articleDao.deleteBatchIds(articleIds);
	}


	/**
	 * 点赞
	 * @param id 文章ID
	 * @return
	 */
	public int updateThumbUp(String id){
		return articleDao.updateThumbUp(id);
	}

}
