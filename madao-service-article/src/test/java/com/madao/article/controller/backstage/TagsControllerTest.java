package com.madao.article.controller.backstage;

import com.madao.article.service.backstage.TagsService;
import com.madao.model.dto.article.TagDto;
import com.madao.utils.JsonData;
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
	com.madao.article.controller.backstage.TagsController tagsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindArticleByCondition() {
	    when(tagsService.findTagsByCondition(any(), any())).thenReturn(null);

	    JsonData<Page<TagDto>> result = tagsController.findArticleByCondition(new TagDto(), null);
	    Assertions.assertEquals(new JsonData<Page<TagDto>>(true, 0, "message", any()), result);
    }

    @Test
    void testFindArticleByPrimaryKey() {
	    when(tagsService.findTagsById(anyString())).thenReturn(new TagDto());

	    JsonData<TagDto> result = tagsController.findArticleByPrimaryKey("id");
	    Assertions.assertEquals(new JsonData<TagDto>(true, 0, "message", any()), result);
    }

    @Test
    void testInsertArticle() {
	    JsonData<Void> result = tagsController.insertArticle(new TagDto());
	    Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
    }

    @Test
    void testUpdateTagsById() {
	    JsonData<Void> result = tagsController.updateTagsById(new TagDto());
	    Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
    }

    @Test
    void testDelete() {
        JsonData<Void> result = tagsController.delete(Arrays.<String>asList("String"));
        Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme