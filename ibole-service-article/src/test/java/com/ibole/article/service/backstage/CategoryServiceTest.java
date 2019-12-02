package com.ibole.article.service.backstage;

import com.ibole.config.CustomPageRequest;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.article.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findCategoryByCondition() {
        QueryVO queryVO = new QueryVO();
        queryVO.setPageable(new CustomPageRequest(1, 10));
        Page<Category> result = categoryService.findCategoryByCondition(new Category(), queryVO);
        Assert.assertTrue(result.getTotalElements() > 0);
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
