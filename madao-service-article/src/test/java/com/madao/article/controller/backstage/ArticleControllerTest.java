package com.madao.article.controller.backstage;

import com.madao.article.filter.CustomFilter;
import com.madao.article.service.backstage.ArticleService;
import com.madao.model.dto.article.ArticleDto;
import com.madao.utils.JsonData;
import com.madao.utils.JsonUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Transactional
class ArticleControllerTest {
	private static final String ENDPOINT_URL = "/article";

	@Mock
	ArticleService articleService;
	@InjectMocks
	ArticleController articleController;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(articleController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()) // 解决Pageable参数问题
				.addFilter(CustomFilter::doFilter)
				.build();
	}

	@Test
	void findAllByPage() throws Exception {

		ArticleDto article = new ArticleDto();
		article.setId("1");

		Page<ArticleDto> page = new PageImpl<>(Collections.singletonList(article));

		Mockito.when(articleService.findArticleByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

		MockHttpServletResponse response = resultActions.andReturn().getResponse();
		String attributeNames = response.getContentAsString();
		JsonData jsonData = JsonUtil.jsonToPojo(attributeNames, JsonData.class);
		System.out.println(jsonData);

		Mockito.verify(articleService, Mockito.times(1)).findArticleByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
		Mockito.verifyNoMoreInteractions(articleService);

	}

	@Test
	void testFindArticleById() {
		Mockito.when(articleService.findArticleById(ArgumentMatchers.anyString())).thenReturn(new ArticleDto());

		JsonData<ArticleDto> result = articleController.findArticleById("id");
		Assertions.assertEquals(new JsonData<ArticleDto>(true, 0, "message", ArgumentMatchers.any()), result);
	}

   /* @Test
    void testInsertArticle() {
        JsonData<Map<String, String>> result = articleController.insertArticle(new ArticleDto(), null);
        Assertions.assertEquals(new JsonData<Map<String, String>>(true, 0, "message", any()), result);
    }

    @Test
    void testUpdateByPrimaryKeySelective() {
        JsonData<Void> result = articleController.updateByPrimaryKeySelective(new ArticleDto(), null);
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
    }*/
}