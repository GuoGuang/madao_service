package com.youyd.search.controller;

import com.youyd.pojo.Result;
import com.youyd.utils.StatusCode;
import com.youyd.search.service.ArticleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 集成ElasticSearch的文章搜索系统
 * @author: LGG
 * @create: 2018-10-14 18:44
 **/

@RestController
@RequestMapping("/article")
public class ArticleSearchController {

    @Autowired
    private ArticleSearchService articleSearchService;


    /**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping(value="/search/{keywords}/{page}/{size}")
    public Result searchArticleByCondition(@PathVariable String keywords,@PathVariable Integer page, @PathVariable Integer size){
        articleSearchService.searchArticleByCondition(keywords,page,size);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
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
