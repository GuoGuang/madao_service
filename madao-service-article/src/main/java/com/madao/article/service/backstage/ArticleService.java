package com.madao.article.service.backstage;

import com.madao.api.UserServiceRpc;
import com.madao.article.mapper.ArticleMapper;
import com.madao.article.mapper.TagMapper;
import com.madao.article.repository.backstage.ArticleRepository;
import com.madao.article.repository.backstage.ArticleTagRepository;
import com.madao.article.repository.backstage.TagRepository;
import com.madao.enums.ArticleAuditStatus;
import com.madao.exception.custom.ResourceNotFoundException;
import com.madao.model.dto.article.ArticleDto;
import com.madao.model.dto.user.UserDto;
import com.madao.model.entity.article.Article;
import com.madao.model.entity.article.ArticleTag;
import com.madao.utils.JsonData;
import com.madao.utils.RedisUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Service
@CacheConfig(cacheNames = "article")
@AllArgsConstructor
public class ArticleService {

	public static final Random RANDOM = new Random();
	private final ArticleRepository articleRepository;
	private final ArticleMapper articleMapper;

	private final RedisUtil redisUtil;

	private final ArticleTagRepository articleTagRepository;
	private final TagRepository tagRepository;
	private final TagMapper tagMapper;
	private final UserServiceRpc userServiceRpc;

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

			List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(articleDto.getTitle())) {
				predicates.add(builder.like(root.get("title"), "%" + articleDto.getTitle() + "%"));
			}
			if (articleDto.getReviewState() != null) {
				predicates.add(builder.equal(root.get("reviewState"), articleDto.getReviewState()));
			}
			if (StringUtils.isNotEmpty(articleDto.getDescription())) {
				predicates.add(builder.like(root.get("description"), "%" + articleDto.getDescription() + "%"));
			}
			return query.where(predicates.toArray(new jakarta.persistence.criteria.Predicate[0])).getRestriction();
		};

		Page<ArticleDto> queryResults = articleRepository.findAll(condition, pageable)
				.map(articleMapper::toDto);

		JsonData<List<UserDto>> userInfoByIds = userServiceRpc.getUserInfoByIds(queryResults.getContent().stream()
				.map(ArticleDto::getUserId).toArray(String[]::new));

		if (userInfoByIds.isStatus()) {
			userInfoByIds.getData().stream().flatMap(userInfo -> queryResults.getContent().stream()
							.filter(articleId -> StringUtils.equals(userInfo.getId(), articleId.getUserId()))
							.peek(articleId -> articleId.setUserName(userInfo.getUserName())))
					.toList();
		}
		return queryResults;
	}


	/**
	 * 根据ID查询实体
	 *
	 * @param articleId 文章id
	 * @return Article
	 */
	public ArticleDto findArticleById(String articleId) {
		ArticleDto articleDto = articleRepository.findById(articleId)
				.map(articleMapper::toDto)
				.orElseThrow(ResourceNotFoundException::new);

		articleDto.setTags(tagRepository.findTagsByArticleId(articleId)
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
			articleDto.setUpvote(RANDOM.nextInt(20));
			articleDto.setVisits(RANDOM.nextInt(98));
			articleDto.setReviewState(ArticleAuditStatus.PASS);
			articleDto.setImportance(RANDOM.nextInt(5));
			if (articleDto.getIsPublic() == null) {
				articleDto.setIsPublic(false);
			}
		} else {
			redisUtil.del("ARTICLE_" + articleDto.getId());
			articleTagRepository.deleteByArticleIdIn(Collections.singletonList(articleDto.getId()));
		}

		Article articleResult = articleRepository.save(articleMapper.toEntity(articleDto));

		List<ArticleTag> articleTags = Arrays.stream(articleDto.getTagsId().split(","))
				.map(tagsId -> new ArticleTag(articleResult.getId(), tagsId))
				.toList();

		articleTagRepository.saveAll(articleTags);

		if (isCreate) {
			redisUtil.lSet("ARTICLE_HOT", articleResult);
		}
	}

	/**
	 * 删除
	 *
	 * @param articleIds:文章id集合
	 */
	public void deleteArticleByIds(List<String> articleIds) {
		articleRepository.deleteBatch(articleIds);
		articleTagRepository.deleteByArticleIdIn(articleIds);
	}

	/**
	 * 审核文章
	 *
	 * @param id
	 */
	public void examine(String id) {
		articleRepository.examine(id);
	}

}
