package com.madao.article.service.backstage;

import com.madao.api.UserServiceRpc;
import com.madao.article.repository.backstage.ArticleRepository;
import com.madao.article.repository.backstage.CategoryRepository;
import com.madao.article.repository.backstage.TagRepository;
import com.madao.model.dto.article.ArticleDto;
import com.madao.utils.JsonData;
import com.madao.utils.RedisUtil;
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
	ArticleRepository articleRepository;
	@Mock
	CategoryRepository categoryRepository;
	@Mock
	RedisUtil redisUtil;
	@Mock
	UserServiceRpc userServiceRpc;
	@Mock
	TagRepository tagRepository;
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
		Assertions.assertNull(result);
	}

	@Test
	void testFindArticleById() {
		ArticleDto result = articleService.findArticleById("articleId");
		Assertions.assertEquals(new ArticleDto(), result);
	}

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
