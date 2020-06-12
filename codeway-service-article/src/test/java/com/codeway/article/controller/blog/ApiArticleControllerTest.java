package com.codeway.article.controller.blog;

import com.codeway.article.service.blog.ApiArticleService;
import com.codeway.db.redis.service.RedisService;
import com.codeway.pojo.article.Article;
import com.codeway.utils.JsonData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class ApiArticleControllerTest {
    @Mock
    ApiArticleService articleService;
    @Mock
    RedisService redisService;
    @InjectMocks
    ApiArticleController apiArticleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindArticleByCondition() {
        when(articleService.findArticleByCondition(any(), anyString(), any())).thenReturn(null);
        when(redisService.lGet(anyString(), anyLong(), anyLong())).thenReturn(Arrays.<Object>asList("lGetResponse"));

        JsonData<Object> result = apiArticleController.findArticleByCondition(new Article(), "keyword", "sortType", null);
        Assertions.assertEquals(new JsonData<Object>(true, 0, "message", new T()), result);
    }

    @Test
    void testFindArticleHot() {
        when(redisService.lGet(anyString(), anyLong(), anyLong())).thenReturn(Arrays.<Object>asList("lGetResponse"));

        JsonData<Object> result = apiArticleController.findArticleHot("sortType");
        Assertions.assertEquals(new JsonData<Object>(true, 0, "message", new T()), result);
    }

    @Test
    void testFindArticleByTagId() {
        when(articleService.findArticleByTagId(anyString(), any())).thenReturn(null);

        JsonData<Page<Article>> result = apiArticleController.findArticleByTagId("tagId", null);
        Assertions.assertEquals(new JsonData<Page<Article>>(true, 0, "message", new T()), result);
    }

    @Test
    void testFindArticleByPrimaryKey() {
        when(articleService.findArticleById(anyString())).thenReturn(new Article());
        when(redisService.get(anyString())).thenReturn("getResponse");
        when(redisService.set(anyString(), any(), anyLong())).thenReturn(true);

        JsonData<Article> result = apiArticleController.findArticleByPrimaryKey("articleId");
        Assertions.assertEquals(new JsonData<Article>(true, 0, "message", new T()), result);
    }

    @Test
    void testUpVote() {
        JsonData<Void> result = apiArticleController.upVote("articleId");
        Assertions.assertEquals(new JsonData<Void>(true, 0, "message", new T()), result);
    }

    @Test
    void testUnUpVote() {
        JsonData<Void> result = apiArticleController.unUpVote("articleId");
        Assertions.assertEquals(new JsonData<Void>(true, 0, "message", new T()), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme