package com.youyd.article.controller.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.article.service.blog.ArticleService;
import com.youyd.enums.StatusEnum;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.Result;
import com.youyd.pojo.article.Article;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 前台网站文章
 * @author: LGG
 * @create: 2019-02-20
 **/

@Api(tags = "文章")
@RestController
@RequestMapping(value = "/api/article",produces = "application/json")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 查询全部数据
     *
     * @return Result
     */
    @ApiOperation(value = "查询文章集合", notes = "Article")
    @GetMapping
    public Result findArticleByCondition(Article article, QueryVO queryVO) {
	    IPage<Article> result = articleService.findArticleByCondition(article,queryVO);
        return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),result);
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return Result
     */
    @ApiOperation(value = "按照id查询文章", notes = "id")
    @GetMapping(value = "/{id}")
    public Result findArticleByPrimaryKey(@PathVariable String id) {
        Article result = articleService.findArticleById(id);
        return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),result);
    }


    /**
     * 增加
     *
     * @param article:文章实例
     */
    @ApiOperation(value = "添加一条新的文章", notes = "id")
    @PostMapping
    public Result insertArticle(@RequestBody Article article) {
        articleService.insertArticle(article);
        return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),null);
    }

    /**
     * 修改
     *
     * @param article:文章实例
     */
    @ApiOperation(value = "按照id修改", notes = "id")
    @PutMapping
    public Result updateByPrimaryKeySelective(@RequestBody Article article) {
        articleService.updateByPrimaryKeySelective(article);
        return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),null);
    }

    /**
     * 删除
     *
     * @param articleIds 文章id
     */
    @ApiOperation(value = "删除", notes = "id")
    @DeleteMapping
    public Result delete(@RequestBody List<String> articleIds) {
        articleService.deleteArticleByIds(articleIds);
        return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),null);
    }


}
