package com.codeif.article.controller.backstage;

import com.codeif.annotation.OptLog;
import com.codeif.article.controller.BaseController;
import com.codeif.article.service.backstage.ArticleService;
import com.codeif.constant.CommonConst;
import com.codeif.pojo.QueryVO;
import com.codeif.pojo.article.Article;
import com.codeif.utils.JsonData;
import com.codeif.utils.OssClientUtil;
import com.querydsl.core.QueryResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Api(tags = "文章管理")
@RestController
@RequestMapping(value = "/article",produces = "application/json")
public class ArticleController extends BaseController {

    private final ArticleService articleService;

	@Autowired
	private OssClientUtil ossClientUtil;

	@Autowired
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

    @ApiOperation(value = "查询文章集合", notes = "Article")
    @GetMapping
    public JsonData<QueryResults<Article>> findArticleByCondition(Article article, QueryVO queryVO) {
        QueryResults<Article> result = articleService.findArticleByCondition(article, queryVO);
        return JsonData.success(result);
    }

    @ApiOperation(value = "按照id查询文章", notes = "id")
    @GetMapping(value = "/{id}")
    public JsonData<Article> findArticleById(@PathVariable String id) {
        Article result = articleService.findArticleById(id);
        return JsonData.success(result);
    }

    @ApiOperation(value = "添加一条新的文章")
    @PostMapping
    @OptLog(operationType = CommonConst.ADD, operationName = "添加一条新的文章")
    public JsonData<Map<String, String>> insertArticle(@RequestBody @Valid Article article, HttpServletRequest request) {
        Map<String, String> userInfo = getUserInfo(request);
        articleService.insertOrUpdateArticle(userInfo, article);
        return JsonData.success(userInfo);
    }

    @ApiOperation(value = "上传文章封面")
    @PutMapping("/thumb")
    @OptLog(operationType = CommonConst.ADD, operationName = "添加一条新的文章")
    public JsonData<String> updateThumb(MultipartFile file) throws IOException {
	    String fileUrl = ossClientUtil.uploadFile(file);
        return JsonData.success(fileUrl);
    }

    @ApiOperation(value = "按照id修改", notes = "id")
    @PutMapping
    @OptLog(operationType = CommonConst.MODIFY, operationName = "修改文章")
    public JsonData<Void> updateByPrimaryKeySelective(@RequestBody @Valid Article article, HttpServletRequest request) {
        Map<String, String> userInfo = getUserInfo(request);
        articleService.insertOrUpdateArticle(userInfo, article);
        return JsonData.success();
    }

    @ApiOperation(value = "删除", notes = "id")
    @DeleteMapping
    @OptLog(operationType = CommonConst.DELETE, operationName = "删除文章")
    public JsonData<Void> deleteArticleByIds(@RequestBody List<String> articleIds) {
        articleService.deleteArticleByIds(articleIds);
        return JsonData.success();
    }

    @ApiOperation(value = "审核当前文章", notes = "id")
    @PutMapping(value = "/examine/{id}")
    public JsonData<Void> examine(@PathVariable String id) {
        articleService.examine(id);
        return JsonData.success();
    }

    @ApiOperation(value = "点赞", notes = "id")
    @PutMapping(value = "/thumbUp/{id}")
    public JsonData<Void> updateThumbUp(@PathVariable String id) {
        articleService.updateThumbUp(id);
        return JsonData.success();
    }
}
