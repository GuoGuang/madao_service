package com.madao.search.controller;

import com.madao.enums.StatusEnum;
import com.madao.model.Result;
import com.madao.search.pojo.Article;
import com.madao.search.service.ArticleSearchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@AllArgsConstructor
public class ArticleSearchController {

    private final ArticleSearchService articleSearchService;

    @GetMapping(value = "/search/{keywords}/{page}/{size}")
    public Result searchArticleByCondition(@PathVariable String keywords, @PathVariable Integer page, @PathVariable Integer size) {
	    List<Article> articles = articleSearchService.searchArticleByCondition(keywords, page, size);
	    return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), articles);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findArticleByPrimaryKey(@PathVariable String id) {
        Article result = articleSearchService.findArticleByPrimaryKey(id);
        return new Result(true,StatusEnum.OK.getCode(),StatusEnum.OK.getMsg(),result);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result insertArticle(@RequestBody Article article) {
        articleSearchService.insertArticle(article);
        return new Result(true,StatusEnum.OK.getCode(),StatusEnum.OK.getMsg(),null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result updateByPrimaryKeySelective(@RequestBody Article article, @PathVariable String id) {
        article.setId(id);
        articleSearchService.updateByPrimaryKey(article);
        return new Result(true,StatusEnum.OK.getCode(),StatusEnum.OK.getMsg(),null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        articleSearchService.deleteById(id);
        return new Result(true,StatusEnum.OK.getCode(),StatusEnum.OK.getMsg(),null);
    }

}
