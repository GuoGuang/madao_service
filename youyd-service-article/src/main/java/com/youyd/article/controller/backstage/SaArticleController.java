package com.youyd.article.controller.backstage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.article.service.backstage.SaArticleService;
import com.youyd.enums.StatusEnum;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.article.Article;
import com.youyd.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
public class SaArticleController {

    private final SaArticleService saArticleService;

	@Autowired
	public SaArticleController(SaArticleService saArticleService) {
		this.saArticleService = saArticleService;
	}

	/**
     * 查询全部数据
     *
     * @return Result
     */
    @ApiOperation(value = "查询文章集合", notes = "Article")
    @GetMapping
    public JsonData findArticleByCondition(Article article, QueryVO queryVO ) {
	    IPage<Article> result = saArticleService.findArticleByCondition(article,queryVO);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),result);
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return Result
     */
    @ApiOperation(value = "按照id查询文章", notes = "id")
    @GetMapping(value = "/{id}")
    public JsonData findArticleById(@PathVariable String id) {
        Article result = saArticleService.findArticleById(id);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),result);
    }


    /**
     * 增加
     * @param article:文章实例
     */
    @ApiOperation(value = "添加一条新的文章")
    @PostMapping
    public JsonData insertArticle(@RequestBody @Valid Article article) {
        saArticleService.insertArticle(article);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),null);
    }

    /**
     * 修改
     * @param article:文章实例
     */
    @ApiOperation(value = "按照id修改", notes = "id")
    @PutMapping
    public JsonData updateByPrimaryKeySelective(@RequestBody Article article) {
        saArticleService.updateByPrimaryKeySelective(article);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),null);
    }

    /**
     * 删除
     *
     * @param articleIds 文章id
     */
    @ApiOperation(value = "删除", notes = "id")
    @DeleteMapping
    public JsonData deleteArticleByIds(@RequestBody List<String> articleIds) {
        saArticleService.deleteArticleByIds(articleIds);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),null);
    }

    /**
     * 审核
     * @param id:文章id
     * @return Result
     */
    @ApiOperation(value = "审核当前文章", notes = "id")
    @PutMapping(value="/examine/{id}")
    public JsonData examine(@PathVariable String id) {
        saArticleService.examine(id);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
    }

    /**
     * 点赞
     * @param id:文章id
     * @return Result
     */
    @ApiOperation(value = "点赞", notes = "id")
    @PutMapping(value="/thumbUp/{id}")
    public JsonData updateThumbUp(@PathVariable String id) {
        saArticleService.updateThumbUp(id);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
    }
}
