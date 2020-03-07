package com.codeif.article.service.backstage;

import com.codeif.api.user.UserServiceRpc;
import com.codeif.article.dao.backstage.ArticleDao;
import com.codeif.article.dao.backstage.CategoryDao;
import com.codeif.article.dao.backstage.TagsDao;
import com.codeif.db.redis.service.RedisService;
import com.codeif.exception.custom.ResourceNotFoundException;
import com.codeif.pojo.QueryVO;
import com.codeif.pojo.article.Article;
import com.codeif.pojo.article.Category;
import com.codeif.pojo.article.QArticle;
import com.codeif.pojo.article.Tags;
import com.codeif.utils.QuerydslUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 文章板块:文章服务
 **/
@Service
public class ArticleService {

	private final ArticleDao articleDao;
	private final CategoryDao categoryDao;

	private final RedisService redisService;

	private final UserServiceRpc userServiceRpc;

	@Autowired
	JPAQueryFactory jpaQueryFactory;
	@Autowired
	TagsDao tagsDao;

	@Autowired
	public ArticleService(ArticleDao articleDao, RedisService redisService,
	                      UserServiceRpc userServiceRpc,CategoryDao categoryDao) {
		this.articleDao = articleDao;
		this.categoryDao = categoryDao;
		this.redisService = redisService;
		this.userServiceRpc = userServiceRpc;
	}

	/**
	 * 查询文章
	 *
	 * @return IPage<Article>
	 */
	public QueryResults<Article> findArticleByCondition(Article article, QueryVO queryVO) {
		QArticle qArticle = QArticle.article;
		Predicate predicate = null;
		OrderSpecifier<?> sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qArticle);
		if (StringUtils.isNotEmpty(article.getTitle())) {
			predicate = ExpressionUtils.and(predicate, qArticle.title.like(article.getTitle()));
		}
		if (article.getReviewState() != null) {
			predicate = ExpressionUtils.and(predicate, qArticle.reviewState.eq(article.getReviewState()));
		}
		if (StringUtils.isNotEmpty(article.getDescription())) {
			predicate = ExpressionUtils.and(predicate, qArticle.description.like(article.getDescription()));
		}
		if (StringUtils.isNotEmpty(queryVO.getFieldSort())) {
			sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qArticle, queryVO.getFieldSort());
		}
		QueryResults<Article> queryResults = jpaQueryFactory
				.selectFrom(qArticle)
				.where(predicate)
				.offset(queryVO.getPageNum())
				.limit(queryVO.getPageSize())
				.orderBy(sortedColumn)
				.fetchResults();
		return queryResults;
	}



	/**
	 * 根据ID查询实体
	 * @param articleId 文章id
	 * @return Article
	 */
	public Article findArticleById(String articleId) {
		Article article = articleDao.findById(articleId).orElseThrow(ResourceNotFoundException::new);
		article.setCategoryId(article.getCategory().getId());
		return article;

	}

	/**
	 * 增加
	 * @param article 实体
	 */
	public void insertOrUpdateArticle(Map<String, String> userInfo,Article article) {
//		article.setUserId(userInfo.get("id"));
		if (StringUtils.isBlank(article.getId())) {
			article.setComment(0);
			article.setType(1);
			article.setUpvote(0);
			article.setVisits(0);
			article.setReviewState(2);
			article.setImportance(0);
			if (article.getIsPublic() == null) {
				article.setIsPublic(0);
			}
//			Optional<Tags> byId = tagsDao.findById("1214844690118086656");
//			HashSet<Tags> objects = new HashSet<>();
//			objects.add(byId.get());
//			article.setTags(objects);
		} else {
			redisService.del("ARTICLE_" + article.getId());
		}


		Optional<Category> byId = categoryDao.findById(article.getCategoryId());
		article.setCategory(byId.get());
		List<Tags> allById = tagsDao.findAllById(Arrays.asList(article.getTagsId().split(",")));
		article.setCategory(byId.get());
		HashSet<Tags> objects = new HashSet<>(allById);
		article.setTags(objects);
		articleDao.save(article);
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
