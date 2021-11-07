package com.madao.search.controller;

import com.madao.enums.StatusEnum;
import com.madao.model.Result;
import com.madao.search.service.ArticleSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章搜索
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@RestController
@RequestMapping("/article")
public class ArticleSearchController {

    private final ArticleSearchService articleSearchService;

    public ArticleSearchController(ArticleSearchService articleSearchService) {
        this.articleSearchService = articleSearchService;
    }

    /**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping(value = "/search/{keywords}/{page}/{size}")
    public Result searchArticleByCondition(@PathVariable String keywords, @PathVariable Integer page, @PathVariable Integer size) {
        articleSearchService.searchArticleByCondition(keywords, page, size);
        return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), null);
    }

    /* *//**
     * 根据ID查询
     *
     * @param id ID
     * @return
     *//*
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findArticleByPrimaryKey(@PathVariable String id) {
        Article result = articleSearchService.findArticleByPrimaryKey(id);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
    }


    *//**
     * 增加
     *
     * @param article
     *//*
    @RequestMapping(method = RequestMethod.POST)
    public Result insertArticle(@RequestBody Article article) {
        articleSearchService.insertArticle(article);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
    }

    *//**
     * 修改
     *
     * @param article
     *//*
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result updateByPrimaryKeySelective(@RequestBody Article article, @PathVariable String id) {
        article.setId(id);
        articleSearchService.updateByPrimaryKeySelective(article);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
    }

    *//**
     * 删除
     *
     * @param id
     *//*
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        articleSearchService.deleteById(id);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
    }*/

}
