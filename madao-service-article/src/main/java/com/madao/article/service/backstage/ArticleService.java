package com.madao.article.service.backstage;

import com.madao.api.UserServiceRpc;
import com.madao.article.dao.backstage.ArticleDao;
import com.madao.article.dao.backstage.ArticleTagDao;
import com.madao.article.dao.backstage.TagDao;
import com.madao.article.mapper.ArticleMapper;
import com.madao.article.mapper.TagMapper;
import com.madao.enums.ArticleAuditStatus;
import com.madao.exception.custom.ResourceNotFoundException;
import com.madao.model.dto.article.ArticleDto;
import com.madao.model.dto.user.UserDto;
import com.madao.model.entity.article.Article;
import com.madao.model.entity.article.ArticleTag;
import com.madao.redis.RedisService;
import com.madao.utils.JsonData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Service
@CacheConfig(cacheNames = "article")
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

        JsonData<List<UserDto>> userInfoByIds = userServiceRpc.getUserInfoByIds(queryResults.getContent().stream()
                .map(ArticleDto::getUserId).toArray(String[]::new));

        if (userInfoByIds.isStatus()) {
            userInfoByIds.getData().stream().flatMap(userInfo -> queryResults.getContent().stream()
                    .filter(articleId -> StringUtils.equals(userInfo.getId(), articleId.getUserId()))
                    .peek(articleId -> articleId.setUserName(userInfo.getUserName())))
                    .collect(Collectors.toList());
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
            if (articleDto.getPublic() == null) {
                articleDto.setPublic(false);
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
            redisService.lSet("ARTICLE_HOT", articleResult);
        }
    }

    /**
     * 删除
     *
     * @param articleIds:文章id集合
     */
    public void deleteArticleByIds(List<String> articleIds) {
        articleDao.deleteBatch(articleIds);
        articleTagDao.deleteByArticleIdIn(articleIds);
    }

    /**
     * 审核文章
     *
     * @param id
     */
    public void examine(String id) {
        articleDao.examine(id);
    }

}
