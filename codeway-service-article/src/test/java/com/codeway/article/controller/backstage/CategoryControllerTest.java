package com.codeway.article.controller.backstage;

import com.codeway.article.service.backstage.CategoryService;
import com.codeway.model.pojo.article.Category;
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

class CategoryControllerTest {
    @Mock
    CategoryService categoryService;
    @InjectMocks
    CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindCategoryByCondition() {
        when(categoryService.findCategoryByCondition(any(), any())).thenReturn(null);

        JsonData<Page<Category>> result = categoryController.findCategoryByCondition(new Category(), null);
        Assertions.assertEquals(new JsonData<Page<Category>>(true, 0, "message", any()), result);
    }

    @Test
    void testFindCategoryByPrimaryKey() {
        when(categoryService.findCategoryById(anyString())).thenReturn(new Category());

        JsonData<Category> result = categoryController.findCategoryByPrimaryKey("id");
        Assertions.assertEquals(new JsonData<Category>(true, 0, "message", any()), result);
    }

    @Test
    void testInsertCategory() {
        JsonData<Void> result = categoryController.insertCategory(new Category());
        Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
    }

    @Test
    void testUpdateByCategorySelective() {
        JsonData<Void> result = categoryController.updateByCategorySelective(new Category());
        Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
    }

    @Test
    void testDeleteCategoryByIds() {
        JsonData<Void> result = categoryController.deleteCategoryByIds(Arrays.<String>asList("String"));
        Assertions.assertEquals(new JsonData<Void>(true, 0, "message", any()), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme