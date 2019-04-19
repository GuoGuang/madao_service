package com.youyd.article.controller.backstage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.article.service.backstage.ArticleService;
import com.youyd.pojo.Result;
import com.youyd.pojo.article.Article;
import com.youyd.utils.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 文章管理
 * /e 区分前后台 uri
 * @author: LGG
 * @create: 2019-01-11
 **/

@Api(tags = "文章")
@RestController
@RequestMapping(value = "/sa/article",produces = "application/json")
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
    public Result findArticleByCondition(Article article) {
	    IPage<Article> result = articleService.findArticleByCondition(article);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
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
        Article result = articleService.findArticleByPrimaryKey(id);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
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
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
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
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
    }

    /**
     * 删除
     *
     * @param articleIds 文章id
     */
    @ApiOperation(value = "删除", notes = "id")
    @DeleteMapping
    public Result delete(@RequestBody List<Long> articleIds) {
        articleService.deleteByIds(articleIds);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
    }

    /**
     * 审核
     * @param id:文章id
     * @return Result
     */
    @ApiOperation(value = "审核当前文章", notes = "id")
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
    @ApiOperation(value = "点赞", notes = "id")
    @PutMapping(value="/thumbUp/{id}")
    public Result updateThumbUp(@PathVariable String id) {
        articleService.updateThumbUp(id);
        return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
    }
}
