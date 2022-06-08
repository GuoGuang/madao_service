package com.madao.article.controller.backstage;

import com.madao.article.service.backstage.CategoryService;
import com.madao.model.dto.article.CategoryDto;
import com.madao.utils.JsonData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.mockito.Mockito.*;

class CategoryControllerTest {
	@Mock
	CategoryService categoryService;
	@InjectMocks
	com.madao.article.controller.backstage.CategoryController categoryController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testFindCategoryByCondition() {
		when(categoryService.findCategoryByCondition(any(), any())).thenReturn(null);

		JsonData<Page<CategoryDto>> result = categoryController.findCategoryByCondition(new CategoryDto(), null);
		Assertions.assertEquals(new JsonData<Page<CategoryDto>>(true, 0, "message", any()), result);
	}

	@Test
	void testFindCategoryByPrimaryKey() {
		when(categoryService.findCategoryById(anyString())).thenReturn(new CategoryDto());

		JsonData<CategoryDto> result = categoryController.findCategoryByPrimaryKey("id");
		Assertions.assertEquals(new JsonData<CategoryDto>(true, 0, "message", any()), result);
	}

	@Test
	void testInsertCategory() {
		JsonData<Void> result = categoryController.insertCategory(new CategoryDto());
		Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
	}

	@Test
	void testUpdateByCategorySelective() {
		JsonData<Void> result = categoryController.updateByCategorySelective(new CategoryDto());
		Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
	}

	@Test
	void testDeleteCategoryByIds() {
		JsonData<Void> result = categoryController.deleteCategoryByIds(List.<String>of("String"));
		Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
	}
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme