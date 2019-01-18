package com.youyd.article.controller;

import com.youyd.pojo.Result;
import com.youyd.utils.StatusCode;
import com.youyd.article.pojo.Article;
import com.youyd.article.service.ArticleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 文章管理
 * @author: LGG
 * @create: 2019-01-11
 **/

@Api(tags = "文章")
@RestController
@RequestMapping(value = "/article",produces = "application/json")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping
    public Result findArticleByCondition() {
        List<Article> result = articleService.findArticleByCondition();
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return Result
     */
    @GetMapping(value = "/{id}")
    public Result findArticleByPrimaryKey(@PathVariable String id) {
        Article result = articleService.findArticleByPrimaryKey(id);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
    }


    /**
     * 增加
     *
     * @param article:文章实例
     */
    @PostMapping
    public Result insertArticle(@RequestBody Article article) {
        articleService.insertArticle(article);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
    }

    /**
     * 修改
     *
     * @param article:文章实例
     */
    @PutMapping(value = "/{id}")
    public Result updateByPrimaryKeySelective(@RequestBody Article article, @PathVariable String id) {
        article.setId(id);
        articleService.updateByPrimaryKeySelective(article);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
    }

    /**
     * 删除
     *
     * @param articleIds 文章id
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable List articleIds) {
        articleService.deleteByIds(articleIds);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
    }

    /**
     * 审核
     * @param id:文章id
     * @return Result
     */
    @PutMapping(value="/examine/{id}")
    public Result examine(@PathVariable String id) {
        articleService.examine(id);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
    }

    /**
     * 点赞
     * @param id:文章id
     * @return Result
     */
    @PutMapping(value="/thumbup/{id}")
    public Result updateThumbup(@PathVariable String id) {
        articleService.updateThumbup(id);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
    }
}
