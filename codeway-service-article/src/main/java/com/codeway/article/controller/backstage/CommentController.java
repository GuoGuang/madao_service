package com.codeway.article.controller.backstage;

import com.codeway.annotation.OptLog;
import com.codeway.article.service.backstage.CommentService;
import com.codeway.enums.OptLogType;
import com.codeway.model.dto.article.CommentDto;
import com.codeway.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Api(tags = "文章评论管理")
@RestController
@RequestMapping(value = "/comment")

public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping()
	@ApiOperation(value = "查询文章评论", notes = "Comment")
	public JsonData<Page<CommentDto>> findCommentByCondition(CommentDto commentDto,
	                                                         @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
		Page<CommentDto> result = commentService.findCommentByCondition(commentDto, pageable);
		return JsonData.success(result);
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "根据ID查询", notes = "Comment")
	public JsonData<CommentDto> findCommentByPrimaryKey(@PathVariable String id) {
		CommentDto result = commentService.findCommentByPrimaryKey(id);
		return JsonData.success(result);
	}

	@PostMapping()
	@OptLog(operationType = OptLogType.ADD, operationName = "增加文章评论")
	@ApiOperation(value = "增加文章评论", notes = "Comment")
	public JsonData<Void> insertComment(@RequestBody @Validated CommentDto commentDto) {
		commentService.saveOrUpdate(commentDto);
		return JsonData.success();
	}

	@PutMapping
	@OptLog(operationType = OptLogType.MODIFY, operationName = "修改文章评论")
	@ApiOperation(value = "修改文章评论", notes = "Comment")
	public JsonData<Void> updateByCommentSelective(@RequestBody @Validated CommentDto commentDto) {
		commentService.saveOrUpdate(commentDto);
		return JsonData.success();
	}

	@DeleteMapping
	@OptLog(operationType = OptLogType.DELETE, operationName = "删除评论")
	@ApiOperation(value = "删除", notes = "Comment")
	public JsonData<Void> deleteByIds(List<String> commentIds) {
		commentService.deleteCommentByIds(commentIds);
		return JsonData.success();
	}
	
}
