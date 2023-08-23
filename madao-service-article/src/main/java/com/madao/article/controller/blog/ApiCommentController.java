package com.madao.article.controller.blog;

import com.madao.article.mapper.CommentMapper;
import com.madao.article.service.blog.ApiCommentService;
import com.madao.model.dto.article.CommentDto;
import com.madao.utils.JsonData;
import com.madao.utils.security.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Tag(name = "前台评论管理")
@RestController
@RequestMapping(value = "/api/ar/comment")
@AllArgsConstructor
public class ApiCommentController {

	private final ApiCommentService apiCommentService;
	private final CommentMapper commentMapper;

	@Operation(summary = "查询我的评论")
	@GetMapping("/my/")
	public JsonData<List<HashMap<Object, Object>>> findMyComment() {
		return JsonData.success(apiCommentService.findMyComment(SecurityUtils.getCurrentUserId()));
	}

	@Operation(summary = "查询评论列表", description = "查询评论列表")
	@GetMapping("/{articleId:\\d+}")
	public JsonData<List<CommentDto>> findArticleByCondition(@PathVariable String articleId) {
		List<CommentDto> result = apiCommentService.findCommentByCondition(articleId);
		return JsonData.success(result);
	}

	@Operation(summary = "点赞评论", description = "点赞评论")
	@PutMapping(value = "/like/{commentId}")
	public JsonData<Void> upVote(@PathVariable String commentId) {
		apiCommentService.upVote(commentId);
		return JsonData.success();
	}

	@Operation(summary = "取消点赞", description = "取消点赞")
	@DeleteMapping(value = "/like/{commentId}")
	public JsonData<Void> unUpVote(@PathVariable String commentId) {
		apiCommentService.unUpVote(commentId);
		return JsonData.success();
	}

	@Operation(summary = "回复评论/添加新评论", description = "回复评论/添加新评论")
	@PostMapping
	public JsonData<Void> addComment(@RequestBody @Validated CommentDto commentDto) {
		apiCommentService.addComment(commentMapper.toEntity(commentDto));
		return JsonData.success();
	}

}
