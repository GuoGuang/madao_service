package com.madao.article.controller.backstage;

import com.madao.annotation.OptLog;
import com.madao.article.controller.BaseController;
import com.madao.article.service.backstage.ArticleService;
import com.madao.config.ratelimit.LimitType;
import com.madao.config.ratelimit.RateLimiter;
import com.madao.enums.OptLogType;
import com.madao.model.dto.article.ArticleDto;
import com.madao.utils.JsonData;
import com.madao.utils.OssClientUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Tag(name = "文章管理")
@RestController
@AllArgsConstructor
@RequestMapping(value = "/article", produces = "application/json")
public class ArticleController implements BaseController {

	private final ArticleService articleService;

	private final OssClientUtil ossClientUtil;

	@Operation(summary = "查询文章集合", description = "Article")
	@GetMapping
	public JsonData<Page<ArticleDto>> findArticleByCondition(ArticleDto articleDto,
	                                                         @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
		Page<ArticleDto> result = articleService.findArticleByCondition(articleDto, pageable);
		return JsonData.success(result);
	}

	@Operation(summary = "按照id查询文章", description = "id")
	@GetMapping(value = "/{id}")
	public JsonData<ArticleDto> findArticleById(@PathVariable String id) {
		ArticleDto result = articleService.findArticleById(id);
		return JsonData.success(result);
	}

	@Operation(summary = "添加一条新的文章")
	@RateLimiter(time = 60 * 3, count = 1, limitType = LimitType.USER_ID)
	@PostMapping
	@OptLog(operationType = OptLogType.ADD, operationName = "添加一条新的文章")
	public JsonData<Map<String, String>> insertArticle(@RequestBody @Validated ArticleDto articleDto, HttpServletRequest request) {
		Map<String, String> userInfo = getUserInfo(request);
		articleService.insertOrUpdateArticle(userInfo, articleDto);
		return JsonData.success();
	}

	@Operation(summary = "上传文章封面")
	@RateLimiter(time = 60 * 3, count = 1, limitType = LimitType.IP)
	@PutMapping("/thumb")
	@OptLog(operationType = OptLogType.ADD, operationName = "上传文章封面")
	public JsonData<String> updateThumb(@RequestParam MultipartFile file) throws IOException {
		String fileUrl = ossClientUtil.uploadFile(file);
		return JsonData.success(fileUrl);
	}

	@Operation(summary = "按照id修改", description = "id")
	@PutMapping
	@OptLog(operationType = OptLogType.MODIFY, operationName = "修改文章")
	public JsonData<Void> updateByPrimaryKeySelective(@RequestBody @Validated ArticleDto articleDto, HttpServletRequest request) {
		Map<String, String> userInfo = getUserInfo(request);
		articleService.insertOrUpdateArticle(userInfo, articleDto);
		return JsonData.success();
	}

	@Operation(summary = "删除", description = "id")
	@DeleteMapping
	@OptLog(operationType = OptLogType.DELETE, operationName = "删除文章")
	public JsonData<Void> deleteArticleByIds(@RequestBody List<String> articleIds) {
		articleService.deleteArticleByIds(articleIds);
		return JsonData.success();
	}

	@Operation(summary = "审核当前文章", description = "id")
	@PutMapping(value = "/examine/{id}")
	public JsonData<Void> examine(@PathVariable String id) {
		articleService.examine(id);
		return JsonData.success();
	}

}
