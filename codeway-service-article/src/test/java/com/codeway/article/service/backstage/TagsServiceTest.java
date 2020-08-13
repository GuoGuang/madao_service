package com.codeway.article.service.backstage;

import com.codeway.article.dao.backstage.ArticleDao;
import com.codeway.article.dao.backstage.TagsDao;
import com.codeway.db.redis.service.RedisService;
import com.codeway.pojo.article.Tag;
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
	    Page<Tag> result = tagsService.findTagsByCondition(new Tag(), null);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testFindTagsById() {
	    Tag result = tagsService.findTagsById("id");
	    Assertions.assertEquals(new Tag(), result);
    }

    @Test
    void testSaveOrUpdate() {
	    tagsService.saveOrUpdate(new Tag());
    }

    @Test
    void testDeleteBatch() {
        tagsService.deleteBatch(Arrays.<String>asList("String"));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme