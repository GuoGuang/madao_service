package com.madao.article.controller.backstage;

import com.madao.article.filter.CustomFilter;
import com.madao.article.service.backstage.ArticleService;
import com.madao.model.dto.article.ArticleDto;
import com.madao.utils.JsonData;
import com.madao.utils.JsonUtil;
import com.madao.utils.OssClientUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@org.springframework.transaction.annotation.Transactional
class ArticleControllerTest {
	@Mock
	ArticleService articleService;
	@Mock
	OssClientUtil ossClientUtil;
	@Mock
	Pageable pageable;
	@InjectMocks
	com.madao.article.controller.backstage.ArticleController articleController;

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
	void testFindArticleByCondition() throws Exception {

		ArticleDto article = new ArticleDto();
//		article.setUserName("foo");
		article.setId("1");

		Page<ArticleDto> page = new PageImpl<>(Arrays.asList(article));

		when(articleService.findArticleByCondition(any(), any())).thenReturn(page);

		ResultActions resultActions = mockMvc.perform(get("/article").accept(MediaType.APPLICATION_JSON));
		resultActions.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.content", hasSize(1)));

		MockHttpServletResponse response = resultActions.andReturn().getResponse();
		String attributeNames = response.getContentAsString();
		JsonData jsonData = JsonUtil.jsonToPojo(attributeNames, JsonData.class);
		System.out.println(jsonData);

		String a = "hahaha";
		int aValue = 9;

		verify(articleService, times(1)).findArticleByCondition(any(), any());
		verifyNoMoreInteractions(articleService);

	}

	@Test
	void testFindArticleById() {
		when(articleService.findArticleById(anyString())).thenReturn(new ArticleDto());

		JsonData<ArticleDto> result = articleController.findArticleById("id");
		Assertions.assertEquals(new JsonData<ArticleDto>(true, 0, "message", any()), result);
	}

	@Test
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
	}
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme