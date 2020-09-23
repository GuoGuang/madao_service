package com.codeway.article.controller.blog;

import com.codeway.article.mapper.CommentMapper;
import com.codeway.article.service.blog.ApiCommentService;
import com.codeway.enums.StatusEnum;
import com.codeway.model.dto.article.CommentDto;
import com.codeway.utils.JsonData;
import com.codeway.utils.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Api(tags = "前台评论管理")
@RestController
@RequestMapping(value = "/api/ar/comment")
public class ApiCommentController {

	private final ApiCommentService apiCommentService;
	private final CommentMapper commentMapper;

	public ApiCommentController(ApiCommentService apiCommentService, CommentMapper commentMapper) {
		this.apiCommentService = apiCommentService;
		this.commentMapper = commentMapper;
	}

	@ApiOperation(value = "查询评论列表", notes = "查询评论列表")
	@GetMapping("/{articleId:\\d+}")
	public JsonData<List<CommentDto>> findArticleByCondition(@PathVariable String articleId) {
		List<CommentDto> result = apiCommentService.findCommentByCondition(articleId);
		return JsonData.success(result);
	}

	@ApiOperation(value = "点赞评论", notes = "点赞评论")
	@PutMapping(value = "/like/{commentId}")
	public JsonData<Void> upVote(@PathVariable String commentId) {
		apiCommentService.upVote(commentId);
		return JsonData.success();
	}

	@ApiOperation(value = "取消点赞", notes = "取消点赞")
	@DeleteMapping(value = "/like/{commentId}")
	public JsonData<Void> unUpVote(@PathVariable String commentId) {
		apiCommentService.unUpVote(commentId);
		return JsonData.success();
	}

	@ApiOperation(value = "", notes = "根据qq号获取用户信息")
	@GetMapping(value = "/user/{qq}")
	public JsonData<Map<String, Object>> findUserInfo(@PathVariable String qq) throws IOException {

		boolean matches = Pattern.matches("^[\\d]{4,10}", qq);
		if (!matches) {
			return JsonData.failed(StatusEnum.PARAM_INVALID, "QQ号格式不对啊，亲");
		}
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			String url = "https://api.uomg.com/api/qq.info?qq=" + qq;
			HttpGet get = new HttpGet(url);
			HttpResponse res = client.execute(get);
			Map<String, Object> qqInfo = JsonUtil.jsonToMap(EntityUtils.toString(res.getEntity()));
			if (!StringUtils.equals("1", qqInfo.get("code") + "")) {
				return JsonData.failed(StatusEnum.PARAM_INVALID, "无效QQ!");
			}
			return JsonData.success(qqInfo);
		}
	}

	@ApiOperation(value = "回复评论/添加新评论", notes = "回复评论/添加新评论")
	@PostMapping
	public JsonData<Void> addComment(@RequestBody @Validated CommentDto commentDto) {
		apiCommentService.addComment(commentMapper.toEntity(commentDto));
		return JsonData.success();
	}

}
