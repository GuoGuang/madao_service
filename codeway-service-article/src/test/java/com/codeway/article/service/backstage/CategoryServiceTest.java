package com.codeway.article.service.backstage;

import com.codeway.article.dao.backstage.CategoryDao;
import com.codeway.db.redis.service.RedisService;
import com.codeway.model.dto.article.CategoryDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.util.Arrays;

class CategoryServiceTest {
    @Mock
    CategoryDao categoryDao;
    @Mock
    RedisService redisService;
    @InjectMocks
    CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindCategoryByCondition() {
	    Page<CategoryDto> result = categoryService.findCategoryByCondition(new CategoryDto(), null);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testFindCategoryById() {
	    CategoryDto result = categoryService.findCategoryById("categoryId");
	    Assertions.assertEquals(new CategoryDto(), result);
    }

    @Test
    void testSaveOrUpdate() {
	    categoryService.saveOrUpdate(new CategoryDto());
    }

    @Test
    void testDeleteCategoryByIds() {
        categoryService.deleteCategoryByIds(Arrays.<String>asList("String"));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme