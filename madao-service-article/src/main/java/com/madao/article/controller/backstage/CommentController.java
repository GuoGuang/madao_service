package com.madao.article.controller.backstage;

import com.madao.annotation.OptLog;
import com.madao.article.service.backstage.CommentService;
import com.madao.enums.OptLogType;
import com.madao.model.dto.article.CommentDto;
import com.madao.utils.JsonData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Tag(name = "文章评论管理")
@RestController
@RequestMapping(value = "/comment")
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping()
	@Operation(summary = "查询文章评论", description = "Comment")
	public JsonData<Page<CommentDto>> findCommentByCondition(CommentDto commentDto,
	                                                         @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
		Page<CommentDto> result = commentService.findCommentByCondition(commentDto, pageable);
		return JsonData.success(result);
	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "根据ID查询", description = "Comment")
	public JsonData<CommentDto> findCommentByPrimaryKey(@PathVariable String id) {
		CommentDto result = commentService.findCommentByPrimaryKey(id);
		return JsonData.success(result);
	}

	@PostMapping()
	@OptLog(operationType = OptLogType.ADD, operationName = "增加文章评论")
	@Operation(summary = "增加文章评论", description = "Comment")
	public JsonData<Void> insertComment(@RequestBody @Validated CommentDto commentDto) {
		commentService.saveOrUpdate(commentDto);
		return JsonData.success();
	}

	@PutMapping
	@OptLog(operationType = OptLogType.MODIFY, operationName = "修改文章评论")
	@Operation(summary = "修改文章评论", description = "Comment")
	public JsonData<Void> updateByCommentSelective(@RequestBody @Validated CommentDto commentDto) {
		commentService.saveOrUpdate(commentDto);
		return JsonData.success();
	}

	@DeleteMapping
	@OptLog(operationType = OptLogType.DELETE, operationName = "删除评论")
	@Operation(summary = "删除", description = "Comment")
	public JsonData<Void> deleteByIds(List<String> commentIds) {
		commentService.deleteCommentByIds(commentIds);
		return JsonData.success();
	}

}
