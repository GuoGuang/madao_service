package com.codeway.article.service.backstage;

import com.codeway.api.user.UserServiceRpc;
import com.codeway.article.dao.backstage.ArticleDao;
import com.codeway.article.dao.backstage.CategoryDao;
import com.codeway.article.dao.backstage.TagsDao;
import com.codeway.config.CustomQueryResults;
import com.codeway.db.redis.service.RedisService;
import com.codeway.model.pojo.article.Article;
import com.codeway.model.pojo.user.User;
import com.codeway.utils.JsonData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.HashMap;

import static org.mockito.Mockito.*;

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
    TagsDao tagsDao;
    @InjectMocks
    ArticleService articleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindArticleByCondition() {
        when(userServiceRpc.findUser()).thenReturn(new JsonData<CustomQueryResults<User>>(true, 0, null, any()));

        Page<Article> result = articleService.findArticleByCondition(new Article(), null);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testFindArticleById() {
        Article result = articleService.findArticleById("articleId");
        Assertions.assertEquals(new Article(), result);
    }

    @Test
    void testInsertOrUpdateArticle() {
        when(redisService.lSet(anyString(), any())).thenReturn(true);

        articleService.insertOrUpdateArticle(new HashMap<String, String>() {{
            put("String", "String");
        }}, new Article());
    }

    @Test
    void testDeleteArticleByIds() {
        articleService.deleteArticleByIds(Arrays.<String>asList("String"));
    }

    @Test
    void testExamine() {
        articleService.examine("id");
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme