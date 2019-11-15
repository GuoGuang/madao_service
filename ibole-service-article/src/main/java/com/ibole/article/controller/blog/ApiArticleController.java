package com.ibole.article.controller.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ibole.article.service.blog.ApiArticleService;
import com.ibole.cache.redis.RedisService;
import com.ibole.constant.ArticleConst;
import com.ibole.enums.StatusEnum;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.article.Article;
import com.ibole.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description 前台网站文章
 * @author LGG
 * @create 2019-02-20
 **/

@Api(tags = "文章")
@RestController
@RequestMapping(value = "/api/article",produces = "application/json")
public class ApiArticleController {

    @Autowired
    private ApiArticleService articleService;
    @Autowired
    private RedisService redisService;

    /**
     * 查询全部数据
     *
     * @return Result
     */
    @ApiOperation(value = "查询文章集合", notes = "Article")
    @GetMapping
    public JsonData findArticleByCondition(Article article, QueryVO queryVO) {
    	if (ArticleConst.SORT_TYPE_HOT.equals(queryVO.getSortType())){
		    List<Object> hotList = redisService.lGet("1", 1, 1);
		    return JsonData.success(hotList);
    	}
	    IPage<Article> result = articleService.findArticleByCondition(article,queryVO);
        return JsonData.success(result);
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return Result
     */
    @ApiOperation(value = "按照id查询文章", notes = "id")
    @GetMapping(value = "/{id}")
    public JsonData findArticleByPrimaryKey(@PathVariable String id) {
        Article result = articleService.findArticleById(id);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),result);
    }



}
