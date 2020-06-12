package com.codeway.article.controller.backstage;

import com.codeway.article.service.backstage.ArticleService;
import com.codeway.pojo.article.Article;
import com.codeway.utils.JsonData;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ArticleControllerTest {
    @Mock
    ArticleService articleService;

    @InjectMocks
    ArticleController articleController;

    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(articleController)
                .build();
    }

    @Test
    public void testFindArticleByCondition() throws Exception {

        List<Article> users = Arrays.asList();

        when(articleService.findArticleByCondition(any(), any())).thenReturn(null);

        mockMvc.perform(get("/article"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].username", is("Foo")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].username", is("Bar")));

        verify(articleService, times(1)).findArticleByCondition(any(), any());
        verifyNoMoreInteractions(articleService);


        JsonData<Page<Article>> result = articleController.findArticleByCondition(new Article(), null);
        Assertions.assertEquals(new JsonData<Page<Article>>(true, 0, "message", any()), result);
    }

    @Test
    void testFindArticleById() {
        when(articleService.findArticleById(anyString())).thenReturn(new Article());

        JsonData<Article> result = articleController.findArticleById("id");
        Assertions.assertEquals(new JsonData<Article>(true, 0, "message", any()), result);
    }

    @Test
    void testInsertArticle() {
        JsonData<Map<String, String>> result = articleController.insertArticle(new Article(), null);
        Assertions.assertEquals(new JsonData<Map<String, String>>(true, 0, "message", any()), result);
    }

    @Test
    void testUpdateByPrimaryKeySelective() {
        JsonData<Void> result = articleController.updateByPrimaryKeySelective(new Article(), null);
        Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
    }

    @Test
    void testDeleteArticleByIds() {
        JsonData<Void> result = articleController.deleteArticleByIds(Arrays.<String>asList("String"));
        Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
    }

    @Test
    void testExamine() {
        JsonData<Void> result = articleController.examine("id");
        Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
    }

    @Test
    void testGetUserInfo() {
        Map<String, String> result = articleController.getUserInfo(null);
        Assertions.assertEquals(new HashMap<String, String>() {{
            put("String", "String");
        }}, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme