package com.youyd.article.service.backstage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.CaseFormat;
import com.youyd.article.dao.backstage.SaArticleDao;
import com.youyd.cache.constant.RedisConstant;
import com.youyd.cache.redis.RedisService;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.article.Article;
import com.youyd.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章板块:文章服务
 * @author : LGG
 * @create : 2018-10-13 16:39
 **/
@Service
public class SaArticleService {

	private final SaArticleDao saArticleDao;

	private final RedisService redisService;

	@Autowired
	public SaArticleService(SaArticleDao saArticleDao, RedisService redisService) {
		this.saArticleDao = saArticleDao;
		this.redisService = redisService;
	}

	/**
	 * 查询文章
	 * @return IPage<Article>
	 */
	public IPage<Article> findArticleByCondition(Article article, QueryVO queryVO ){
		Page<Article> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());
		QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
		if (StringUtils.isNotEmpty(article.getTitle())) {
			queryWrapper.lambda().like(Article::getTitle, article.getTitle());
		}
		if (article.getReviewState() != null ) {
			queryWrapper.lambda().like(Article::getReviewState, article.getReviewState());
		}
		if (StringUtils.isNotEmpty(article.getDescription())) {
			queryWrapper.lambda().like(Article::getDescription, article.getDescription());
		}
		if (queryVO.getOrderBy() != null && StringUtils.isNotEmpty(queryVO.getFieldSort())){
			// 驼峰下划线
			String fieldSort = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryVO.getFieldSort());
			queryWrapper.orderBy(true,queryVO.getOrderBy(),fieldSort);
		}
		return saArticleDao.selectPage(pr, queryWrapper);
	}



	/**
	 * 根据ID查询实体
	 * @param articleId 文章id
	 * @return Article
	 */
	public Article findArticleById(String articleId) {
		Object mapJson = redisService.get(RedisConstant.REDIS_KEY_ARTICLE + articleId);
		Article article = JsonUtil.mapToPojo(mapJson, Article.class);
		// 如果缓存没有则到数据库查询并放入缓存,有效期一天
		if(article==null) {
			article = saArticleDao.selectById(articleId);
			redisService.set(RedisConstant.REDIS_KEY_ARTICLE+ articleId, article,RedisConstant.REDIS_TIME_DAY);
		}
		return article;
	}

	/**
	 * 增加
	 * @param article 实体
	 */
	public void insertArticle(Article article) {
		saArticleDao.insert(article);
	}

	/**
	 * 修改
	 * @param article 实体
	 */
	public void updateByPrimaryKeySelective(Article article) {
		redisService.del( "ARTICLE_" + article.getId());
		saArticleDao.updateById(article);
	}

	/**
	 * 删除
	 * @param articleIds:文章id集合
	 */
	public void deleteArticleByIds(List<String> articleIds) {
		saArticleDao.deleteBatchIds(articleIds);
	}

	/**
	 * 审核文章
	 * @param id
	 */
	public void examine(String id){
		saArticleDao.examine(id);
	}

	/**
	 * 点赞
	 * @param id 文章ID
	 */
	public int updateThumbUp(String id){
		return saArticleDao.updateThumbUp(id);
	}

}
