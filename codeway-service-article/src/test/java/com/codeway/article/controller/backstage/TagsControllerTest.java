package com.codeway.article.controller.backstage;

import com.codeway.article.service.backstage.TagsService;
import com.codeway.pojo.article.Tag;
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

class TagsControllerTest {
    @Mock
    TagsService tagsService;
    @InjectMocks
    TagsController tagsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindArticleByCondition() {
	    when(tagsService.findTagsByCondition(any(), any())).thenReturn(null);

	    JsonData<Page<Tag>> result = tagsController.findArticleByCondition(new Tag(), null);
	    Assertions.assertEquals(new JsonData<Page<Tag>>(true, 0, "message", any()), result);
    }

    @Test
    void testFindArticleByPrimaryKey() {
	    when(tagsService.findTagsById(anyString())).thenReturn(new Tag());

	    JsonData<Tag> result = tagsController.findArticleByPrimaryKey("id");
	    Assertions.assertEquals(new JsonData<Tag>(true, 0, "message", any()), result);
    }

    @Test
    void testInsertArticle() {
	    JsonData<Void> result = tagsController.insertArticle(new Tag());
        Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
    }

    @Test
    void testUpdateTagsById() {
	    JsonData<Void> result = tagsController.updateTagsById(new Tag());
        Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
    }

    @Test
    void testDelete() {
        JsonData<Void> result = tagsController.delete(Arrays.<String>asList("String"));
        Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme