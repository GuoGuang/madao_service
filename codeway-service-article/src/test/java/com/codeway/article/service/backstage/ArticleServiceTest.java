package com.codeway.article.service.backstage;

import com.codeway.api.user.UserServiceRpc;
import com.codeway.article.dao.backstage.ArticleDao;
import com.codeway.article.dao.backstage.CategoryDao;
import com.codeway.article.dao.backstage.TagDao;
import com.codeway.db.redis.service.RedisService;
import com.codeway.model.dto.article.ArticleDto;
import com.codeway.utils.JsonData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class ArticleServiceTest {
    @Mock
    ArticleDao articleDao;
    @Mock
    CategoryDao categoryDao;
    @Mock
    RedisService redisService;
	@Mock
	UserServiceRpc userServiceRpc;
	@Mock
	TagDao tagDao;
	@InjectMocks
	ArticleService articleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindArticleByCondition() {
	    when(userServiceRpc.getUserInfo("admin")).thenReturn(new JsonData<>(true, 0, null, any()));

	    Page<ArticleDto> result = articleService.findArticleByCondition(new ArticleDto(), null);
	    Assertions.assertEquals(null, result);
    }

    @Test
    void testFindArticleById() {
	    ArticleDto result = articleService.findArticleById("articleId");
	    Assertions.assertEquals(new ArticleDto(), result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme