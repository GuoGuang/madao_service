package com.codeway.article.service.backstage;

import com.codeway.pojo.QueryVO;
import com.codeway.pojo.article.Category;
import com.querydsl.core.QueryResults;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findCategoryByCondition() {
//        QueryVO queryVO = new QueryVO();
//        QueryResults<Category> result = categoryService.findCategoryByCondition(new Category(), queryVO);
//        Assert.assertTrue(result.getTotal() > 0);
    }

    @Test
    public void findCategoryById() {
        Category categoryById = categoryService.findCategoryById("3");
        Assert.assertNotNull(categoryById);
    }

    @Test
    public void saveOrUpdate() {
        Category category = new Category();
        category.setName("666666666666");
        category.setSummary("666666666666");
        categoryService.saveOrUpdate(category);
    }

    @Test
    public void deleteCategoryByIds() {
        categoryService.deleteCategoryByIds(Arrays.asList("1200660931730870272"));
    }
}
