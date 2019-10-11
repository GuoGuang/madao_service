package com.youyd.article.controller.backstage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.annotation.OptLog;
import com.youyd.article.controller.BaseController;
import com.youyd.article.service.backstage.ArticleService;
import com.youyd.constant.CommonConst;
import com.youyd.enums.StatusEnum;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.article.Article;
import com.youyd.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @description 文章管理
 * /e 区分前后台 uri
 * @author LGG
 * @create 2019-01-11
 **/

@Api(tags = "文章")
@RestController
@RequestMapping(value = "/article",produces = "application/json")
public class ArticleController extends BaseController {

    private final ArticleService articleService;

	@Autowired
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	/**
     * 查询全部数据
     *
     * @return Result
     */
    @ApiOperation(value = "查询文章集合", notes = "Article")
    @GetMapping
    public JsonData findArticleByCondition(Article article, QueryVO queryVO ) {
	    IPage<Article> result = articleService.findArticleByCondition(article,queryVO);
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
        Article result = articleService.findArticleById(id);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),result);
    }


    /**
     * 增加
     * @param article:文章实例
     */
    @ApiOperation(value = "添加一条新的文章")
    @PostMapping
    @OptLog(operationType= CommonConst.ADD,operationName="添加一条新的文章")
    public JsonData insertArticle(@RequestBody @Valid Article article, HttpServletRequest request) {
	    Map<String, String> userInfo = getUserInfo(request);
	    articleService.insertOrUpdateArticle(userInfo,article);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),null);
    }

    /**
     * 修改
     * @param article:文章实例
     */
    @ApiOperation(value = "按照id修改", notes = "id")
    @PutMapping
    @OptLog(operationType= CommonConst.MODIFY,operationName="修改文章")
    public JsonData updateByPrimaryKeySelective(@RequestBody @Valid Article article, HttpServletRequest request) {
	    Map<String, String> userInfo = getUserInfo(request);
        articleService.insertOrUpdateArticle(userInfo,article);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),null);
    }

    /**
     * 删除
     *
     * @param articleIds 文章id
     */
    @ApiOperation(value = "删除", notes = "id")
    @DeleteMapping
    @OptLog(operationType= CommonConst.DELETE,operationName="删除文章")
    public JsonData deleteArticleByIds(@RequestBody List<String> articleIds) {
        articleService.deleteArticleByIds(articleIds);
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
        articleService.examine(id);
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
        articleService.updateThumbUp(id);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
    }
}
