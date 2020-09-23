package com.codeway.article.controller.backstage;

import com.codeway.annotation.OptLog;
import com.codeway.article.controller.BaseController;
import com.codeway.article.service.backstage.ArticleService;
import com.codeway.enums.OptLogType;
import com.codeway.model.dto.article.ArticleDto;
import com.codeway.utils.JsonData;
import com.codeway.utils.OssClientUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Api(tags = "文章管理")
@RestController
@RequestMapping(value = "/article",produces = "application/json")
public class ArticleController implements BaseController {

    private final ArticleService articleService;

	private final OssClientUtil ossClientUtil;

	public ArticleController(ArticleService articleService, OssClientUtil ossClientUtil) {
		this.articleService = articleService;
        this.ossClientUtil = ossClientUtil;
    }

	@ApiOperation(value = "查询文章集合", notes = "Article")
	@GetMapping
	public JsonData<Page<ArticleDto>> findArticleByCondition(ArticleDto articleDto,
	                                                         @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
		Page<ArticleDto> result = articleService.findArticleByCondition(articleDto, pageable);
		return JsonData.success(result);
	}

	@ApiOperation(value = "按照id查询文章", notes = "id")
	@GetMapping(value = "/{id}")
	public JsonData<ArticleDto> findArticleById(@PathVariable String id) {
		ArticleDto result = articleService.findArticleById(id);
		return JsonData.success(result);
	}

	@ApiOperation(value = "添加一条新的文章")
	@PostMapping
//    @OptLog(operationType = OptLogType.ADD, operationName = "添加一条新的文章")
	public JsonData<Map<String, String>> insertArticle(@RequestBody @Validated ArticleDto articleDto, HttpServletRequest request) {
		Map<String, String> userInfo = getUserInfo(request);
		articleService.insertOrUpdateArticle(userInfo, articleDto);
		return JsonData.success();
	}

    @ApiOperation(value = "上传文章封面")
    @PutMapping("/thumb")
//    @OptLog(operationType = OptLogType.ADD, operationName = "上传文章封面")
    public JsonData<String> updateThumb(MultipartFile file) throws IOException {
	    String fileUrl = ossClientUtil.uploadFile(file);
        return JsonData.success(fileUrl);
    }

	@ApiOperation(value = "按照id修改", notes = "id")
	@PutMapping
	@OptLog(operationType = OptLogType.MODIFY, operationName = "修改文章")
	public JsonData<Void> updateByPrimaryKeySelective(@RequestBody @Validated ArticleDto articleDto, HttpServletRequest request) {
		Map<String, String> userInfo = getUserInfo(request);
		articleService.insertOrUpdateArticle(userInfo, articleDto);
		return JsonData.success();
	}

    @ApiOperation(value = "删除", notes = "id")
    @DeleteMapping
    @OptLog(operationType = OptLogType.DELETE, operationName = "删除文章")
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

}
