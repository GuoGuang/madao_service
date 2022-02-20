package com.madao.article.controller.blog;

import com.madao.article.service.blog.ApiArticleService;
import com.madao.model.dto.article.ArticleDto;
import com.madao.model.entity.article.Article;
import com.madao.utils.JsonData;
import com.madao.utils.RedisUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ApiArticleControllerTest {
    @Mock
    ApiArticleService articleService;
    @Mock
    RedisUtil redisUtil;
    @InjectMocks
    com.madao.article.controller.blog.ApiArticleController apiArticleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindArticleByCondition() {
        when(articleService.findArticleByCondition(any(), anyString(), any())).thenReturn(null);
        when(redisUtil.lGet(anyString(), anyLong(), anyLong())).thenReturn(Arrays.asList());

        JsonData<Page<ArticleDto>> result = apiArticleController.findArticleByCondition(new ArticleDto(), "keyword", "sortType", null);
        Assertions.assertEquals(new JsonData<Object>(true, 0, "message", any()), result);
    }

    @Test
    void testFindArticleHot() {
        when(redisUtil.lGet(anyString(),anyLong(), anyLong())).thenReturn(Arrays.asList());

        JsonData<List<Article>> result = apiArticleController.findArticleHot("sortType");
        Assertions.assertEquals(new JsonData<Object>(true, 0, "message", any()), result);
    }

    @Test
    void testFindArticleByTagId() {
        when(articleService.findArticleByTagId(anyString(), any())).thenReturn(null);

        JsonData<Page<ArticleDto>> result = apiArticleController.findArticleByTagId("tagId", null);
        Assertions.assertEquals(new JsonData<Page<ArticleDto>>(true, 0, "message", any()), result);
    }

    @Test
    void testFindArticleByPrimaryKey() {
        when(articleService.findArticleById(anyString())).thenReturn(new ArticleDto());
        when(redisUtil.get(anyString())).thenReturn(null);
        when(redisUtil.set(anyString(), any(), anyLong())).thenReturn(true);

        JsonData<Object> result = apiArticleController.findArticleByPrimaryKey("articleId");
        Assertions.assertEquals(new JsonData<ArticleDto>(true, 0, "message", any()), result);
    }

    @Test
    void testUpVote() {
        JsonData<Void> result = apiArticleController.upVote("articleId");
        Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
    }

    @Test
    void testUnUpVote() {
        JsonData<Void> result = apiArticleController.unUpVote("articleId");
        Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme