package com.ibole.article.service.backstage;

import com.ibole.api.user.UserServiceRpc;
import com.ibole.article.dao.backstage.ArticleDao;
import com.ibole.constant.CommonConst;
import com.ibole.constant.RedisConstant;
import com.ibole.db.redis.service.RedisService;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.article.Article;
import com.ibole.pojo.user.User;
import com.ibole.utils.DateUtil;
import com.ibole.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 文章板块:文章服务
 **/
@Service
public class ArticleService {

	private final ArticleDao articleDao;

	private final RedisService redisService;

	private final UserServiceRpc userServiceRpc;

	@Autowired
	public ArticleService(ArticleDao articleDao, RedisService redisService,UserServiceRpc userServiceRpc) {
		this.articleDao = articleDao;
		this.redisService = redisService;
		this.userServiceRpc = userServiceRpc;
	}

	/**
	 * 查询文章
	 *
	 * @return IPage<Article>
	 */
	public Page<Article> findArticleByCondition(Article article, QueryVO queryVO) {

		Specification<Article> condition = (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(article.getTitle())) {
				predicates.add(builder.like(root.get("title"), "%" + article.getTitle() + "%"));
			}
			if (article.getReviewState() != null) {
				predicates.add(builder.like(root.get("reviewState"), "%" + article.getReviewState() + "%"));
			}
			if (StringUtils.isNotEmpty(article.getDescription())) {
				predicates.add(builder.like(root.get("description"), "%" + article.getDescription() + "%"));
			}
			if (queryVO.getOrderBy() != null && StringUtils.isNotEmpty(queryVO.getFieldSort())) {
				// 驼峰下划线
				// String fieldSort = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryVO.getFieldSort());
				// queryWrapper.orderBy(true,queryVO.getOrderBy(),fieldSort);
			}
			Predicate[] ps = new Predicate[predicates.size()];
			query.where(builder.and(predicates.toArray(ps)));
			query.orderBy(builder.desc(root.get("createAt").as(Long.class)));
			return null;
		};

		Page<Article> articlePage = articleDao.findAll(condition, queryVO.getPageable());
		List<User> userList = userServiceRpc.findUser().getData().getContent();
		articlePage.getContent().forEach(
				articleUser -> userList.forEach(
						user -> {
							if (user.getId().equals(articleUser.getUserId())) {
								articleUser.setUserName(user.getUserName());
							}
						}
				)
		);
		return articlePage;
	}



	/**
	 * 根据ID查询实体
	 * @param articleId 文章id
	 * @return Article
	 */
	public Article findArticleById(String articleId) {
		Object mapJson = redisService.get(RedisConstant.REDIS_KEY_ARTICLE + articleId);
		Article article;
		if (mapJson == null) {
			Optional<Article> byId = articleDao.findById(articleId);
			article = byId.get();
			redisService.set(RedisConstant.REDIS_KEY_ARTICLE + articleId, article, CommonConst.TIME_OUT_DAY);
			return article;
		} else {
			article = JsonUtil.jsonToPojo(mapJson.toString(), Article.class);
			article = (Article)mapJson;
		}
		return article;
	}

	/**
	 * 增加
	 * @param article 实体
	 */
	public void insertOrUpdateArticle(Map<String, String> userInfo,Article article) {
		article.setUserId(userInfo.get("id"));
		if (StringUtils.isBlank(article.getId())) {
			article.setComment(0);
			article.setUpvote(0);
			article.setVisits(0);
			article.setReviewState(2);
			article.setImportance(0);
			article.setCreateAt(DateUtil.getTimestamp());
			article.setUpdateAt(DateUtil.getTimestamp());
			if (article.getIsPublic() == null) {
				article.setIsPublic(0);
			}
			articleDao.save(article);
		} else {
			redisService.del("ARTICLE_" + article.getId());
			article.setUpdateAt(DateUtil.getTimestamp());
			articleDao.save(article);
		}
	}

	/**
	 * 删除
	 * @param articleIds:文章id集合
	 */
	public void deleteArticleByIds(List<String> articleIds) {
		articleDao.deleteBatch(articleIds);
	}

	/**
	 * 审核文章
	 * @param id
	 */
	public void examine(String id){
		articleDao.examine(id);
	}

	/**
	 * 点赞
	 *
	 * @param id 文章ID
	 */
	public void updateThumbUp(String id) {
		articleDao.updateThumbUp(id);
	}

}
