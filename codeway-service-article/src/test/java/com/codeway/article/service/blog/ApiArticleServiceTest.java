package com.codeway.article.service.blog;

import com.codeway.article.dao.backstage.TagsDao;
import com.codeway.article.dao.blog.ApiArticleDao;
import com.codeway.article.service.backstage.CategoryService;
import com.codeway.db.redis.service.RedisService;
import com.codeway.pojo.article.Article;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class ApiArticleServiceTest {
    @Mock
    ApiArticleDao articleDao;
    @Mock
    CategoryService categoryService;
    @Mock
    TagsDao tagsDao;
    @Mock
    RedisService redisService;
    @InjectMocks
    ApiArticleService apiArticleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindArticleByCondition() {
        Page<Article> result = apiArticleService.findArticleByCondition(new Article(), "keyword", null);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testFindArticleByTagId() {
        when(articleDao.findArticleByTagId(anyString(), any())).thenReturn(null);

        Page<Article> result = apiArticleService.findArticleByTagId("tagId", null);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testFindArticleById() {
        when(articleDao.findRelatedByRand()).thenReturn(Arrays.<Article>asList(new Article()));

        Article result = apiArticleService.findArticleById("articleId");
        Assertions.assertEquals(new Article(), result);
    }

    @Test
    void testInsertArticle() {
        apiArticleService.insertArticle(new Article());
    }

    @Test
    void testUpdateByPrimaryKeySelective() {
        apiArticleService.updateByPrimaryKeySelective(new Article());
    }

    @Test
    void testDeleteArticleByIds() {
        apiArticleService.deleteArticleByIds(Arrays.<String>asList("String"));
    }

    @Test
    void testUpdateUpVote() {
        apiArticleService.updateUpVote("id");
    }

    @Test
    void testUpdateUnUpVote() {
        apiArticleService.updateUnUpVote("id");
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme