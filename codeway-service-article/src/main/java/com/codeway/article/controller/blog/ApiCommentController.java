package com.codeway.article.controller.blog;

import com.codeway.article.service.blog.ApiCommentService;
import com.codeway.enums.StatusEnum;
import com.codeway.model.pojo.article.Comment;
import com.codeway.utils.JsonData;
import com.codeway.utils.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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

    public ApiCommentController(ApiCommentService apiCommentService) {
        this.apiCommentService = apiCommentService;
    }

	@ApiOperation(value = "查询评论列表", notes = "查询评论列表")
	@GetMapping("/{articleId}")
	public JsonData<List<Comment>> findArticleByCondition(@PathVariable String articleId) {
		List<Comment> result = apiCommentService.findCommentByCondition(articleId);
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
	public JsonData<Map> findUserInfo(@PathVariable String qq) throws IOException {

		boolean matches = Pattern.matches("^[\\d]{4,10}", qq);
		if (!matches) {
			return JsonData.failed(StatusEnum.PARAM_INVALID, "QQ号格式不对啊，亲");
		}
		HttpClient client = HttpClients.createDefault();
		String url = "https://api.uomg.com/api/qq.info?qq=" + qq;
		HttpGet get = new HttpGet(url);
		HttpResponse res = client.execute(get);
		Map<String, Object> QqInfo = JsonUtil.jsonToMap(EntityUtils.toString(res.getEntity()));
		if (!StringUtils.equals("1", QqInfo.get("code") + "")) {
			return JsonData.failed(StatusEnum.PARAM_INVALID, "无效QQ!");
		}
		return JsonData.success(QqInfo);
	}

	@ApiOperation(value = "回复评论/添加新评论", notes = "回复评论/添加新评论")
	@PostMapping
	public JsonData<Void> addComment(@RequestBody Comment comment) {
		apiCommentService.addComment(comment);
		return JsonData.success();
	}

}
