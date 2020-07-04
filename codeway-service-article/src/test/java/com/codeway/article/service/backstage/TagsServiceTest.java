package com.codeway.article.service.backstage;

import com.codeway.article.dao.backstage.ArticleDao;
import com.codeway.article.dao.backstage.TagsDao;
import com.codeway.db.redis.service.RedisService;
import com.codeway.pojo.article.Tags;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.util.Arrays;

class TagsServiceTest {
    @Mock
    TagsDao tagsDao;
    @Mock
    ArticleDao articleDao;
    @Mock
    RedisService redisService;
    @InjectMocks
    TagsService tagsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindTagsByCondition() {
        Page<Tags> result = tagsService.findTagsByCondition(new Tags(), null);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testFindTagsById() {
        Tags result = tagsService.findTagsById("id");
        Assertions.assertEquals(new Tags(), result);
    }

    @Test
    void testSaveOrUpdate() {
        tagsService.saveOrUpdate(new Tags());
    }

    @Test
    void testDeleteBatch() {
        tagsService.deleteBatch(Arrays.<String>asList("String"));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme