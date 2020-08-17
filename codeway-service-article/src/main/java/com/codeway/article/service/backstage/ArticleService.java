package com.codeway.article.service.backstage;

import com.codeway.api.user.UserServiceRpc;
import com.codeway.article.dao.backstage.ArticleDao;
import com.codeway.article.dao.backstage.ArticleTagDao;
import com.codeway.article.dao.backstage.TagDao;
import com.codeway.article.mapper.ArticleMapper;
import com.codeway.article.mapper.TagMapper;
import com.codeway.db.redis.service.RedisService;
import com.codeway.enums.ArticleAuditStatus;
import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.model.dto.article.ArticleDto;
import com.codeway.model.dto.user.UserDto;
import com.codeway.model.pojo.article.Article;
import com.codeway.model.pojo.article.ArticleTag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleService {

	private final ArticleDao articleDao;
	private final ArticleMapper articleMapper;

	private final RedisService redisService;

	private final ArticleTagDao articleTagDao;
	private final TagDao tagDao;
	private final TagMapper tagMapper;
	private final UserServiceRpc userServiceRpc;

	public ArticleService(ArticleDao articleDao,
	                      ArticleMapper articleMapper,
	                      RedisService redisService,
	                      ArticleTagDao articleTagDao,
	                      TagDao tagDao, TagMapper tagMapper, UserServiceRpc userServiceRpc) {
		this.articleDao = articleDao;
		this.articleMapper = articleMapper;
		this.redisService = redisService;
		this.articleTagDao = articleTagDao;
		this.tagDao = tagDao;
		this.tagMapper = tagMapper;
		this.userServiceRpc = userServiceRpc;
	}

	/**
	 * 查询文章
	 *
	 * @return IPage<Article>
	 */
	public Page<ArticleDto> findArticleByCondition(ArticleDto articleDto, Pageable pageable) {
		Specification<Article> condition = (root, query, builder) -> {

			/* OR查询
			List<Predicate> predicates = new ArrayList<>();
			Predicate name = null;
			if (StringUtils.isNotEmpty(article.getTitle())) {
				name = builder.like(root.get("name"), "%" + article.getTitle() + "%");
			}
			Predicate address = root.get("address").in(Arrays.asList("John", "Raj"));
			predicates.add(builder.or(address, name));
			return query.where(predicates.toArray(new Predicate[0])).getRestriction();*/

			List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(articleDto.getTitle())) {
				predicates.add(builder.like(root.get("title"), "%" + articleDto.getTitle() + "%"));
			}
			if (articleDto.getReviewState() != null) {
				predicates.add(builder.equal(root.get("reviewState"), articleDto.getReviewState()));
			}
			if (StringUtils.isNotEmpty(articleDto.getDescription())) {
				predicates.add(builder.like(root.get("description"), "%" + articleDto.getDescription() + "%"));
			}
			return query.where(predicates.toArray(new javax.persistence.criteria.Predicate[0])).getRestriction();
		};
		Page<ArticleDto> queryResults = articleDao.findAll(condition, pageable)
				.map(articleMapper::toDto);
		queryResults.getContent().forEach(
				articleParam -> {
					UserDto userInfo = userServiceRpc.getUserInfo(
							new UserDto(articleParam.getUserId()))
							.getData();
					if (userInfo != null && userInfo.getId().equals(articleParam.getUserId())) {
						articleParam.setUserName(userInfo.getUserName());
					}
				}
		);
		return queryResults;
	}


	/**
	 * 根据ID查询实体
	 *
	 * @param articleId 文章id
	 * @return Article
	 */
	public ArticleDto findArticleById(String articleId) {
		ArticleDto articleDto = articleDao.findById(articleId)
				.map(articleMapper::toDto)
				.orElseThrow(ResourceNotFoundException::new);

		articleDto.setTags(tagDao.findTagsByArticleId(articleId)
				.map(tagMapper::toDto)
				.orElse(Collections.emptyList()));

		return articleDto;
	}

	public void insertOrUpdateArticle(Map<String, String> userInfo, ArticleDto articleDto) {
		articleDto.setUserId(userInfo.get("id"));
		boolean isCreate = false;
		if (StringUtils.isBlank(articleDto.getId())) {
			isCreate = true;
			articleDto.setComment(0);
			articleDto.setUpvote(new Random().nextInt(20));
			articleDto.setVisits(new Random().nextInt(98));
			articleDto.setReviewState(ArticleAuditStatus.PASS);
			articleDto.setImportance(new Random().nextInt(5));
			if (articleDto.getIsPublic() == null) {
				articleDto.setIsPublic(false);
			}
		} else {
			redisService.del("ARTICLE_" + articleDto.getId());
			articleTagDao.deleteByArticleIdIn(Collections.singletonList(articleDto.getId()));
		}

		Article articleResult = articleDao.save(articleMapper.toEntity(articleDto));

		List<ArticleTag> articleTags = Arrays.stream(articleDto.getTagsId().split(","))
				.map(tagsId -> new ArticleTag(articleResult.getId(), tagsId))
				.collect(Collectors.toList());

		articleTagDao.saveAll(articleTags);

		if (isCreate) {
			redisService.lSet("ARTICLE_HOT", articleDto);
		}
	}

	/**
	 * 删除
	 * @param articleIds:文章id集合
	 */
	public void deleteArticleByIds(List<String> articleIds) {
		articleDao.deleteBatch(articleIds);
		articleTagDao.deleteByArticleIdIn(articleIds);
	}

	/**
	 * 审核文章
	 * @param id
	 */
	public void examine(String id){
		articleDao.examine(id);
	}

}
