package com.codeif.article.service.backstage;

import com.codeif.pojo.QueryVO;
import com.codeif.pojo.article.Article;
import com.codeif.utils.JsonUtil;
import com.codeif.utils.LogBack;
import com.querydsl.core.QueryResults;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    public void findArticleByCondition() {
        QueryVO queryVO = new QueryVO();
        QueryResults<Article> result = articleService.findArticleByCondition(new Article(), queryVO);
        LogBack.info(JsonUtil.toJsonString(result));
        Assert.assertTrue(result.getTotal() > 0);
    }

    @Test
    public void findArticleById() {
        Article articleById = articleService.findArticleById("1157518139778985985");
        Assert.assertNotNull(articleById);
    }

    @Test
    public void insertOrUpdateArticle() {
        Article article = new Article();
        article.setUserName("666666666666");
        article.setTitle("66666");
        article.setOrigin(1);
        article.setTitle("66666");
        article.setContent("66666");
        article.setDescription("66666");
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        articleService.insertOrUpdateArticle(objectObjectHashMap, article);
    }

    @Test
    public void deleteArticleByIds() {
        articleService.deleteArticleByIds(Arrays.asList("1170303530789548034", "1157770104106725378"));
    }

}
