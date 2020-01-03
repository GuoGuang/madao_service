package com.ibole.article.controller.backstage;

import com.ibole.annotation.OptLog;
import com.ibole.article.service.backstage.CommentService;
import com.ibole.constant.CommonConst;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.article.Comment;
import com.ibole.utils.JsonData;
import com.querydsl.core.QueryResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "文章评论管理")
@RestController
@RequestMapping(value = "/comment",produces = "application/json")

public class CommentController {

	private final CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping()
	@ApiOperation(value = "查询文章评论", notes = "Comment")
	public JsonData<QueryResults<Comment>> findCommentByCondition(Comment comment, QueryVO queryVO) {
		QueryResults<Comment> result = commentService.findCommentByCondition(comment, queryVO);
		return JsonData.success(result);
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "根据ID查询", notes = "Comment")
	public JsonData<Comment> findCommentByPrimaryKey(@PathVariable String id) {
		Comment result = commentService.findCommentByPrimaryKey(id);
		return JsonData.success(result);
	}

	@PostMapping()
	@OptLog(operationType = CommonConst.ADD, operationName = "增加文章评论")
	@ApiOperation(value = "增加文章评论", notes = "Comment")
	public JsonData<Void> insertComment(@RequestBody @Valid Comment comment) {
		commentService.saveOrUpdate(comment);
		return JsonData.success();
	}

	@PutMapping
	@OptLog(operationType = CommonConst.MODIFY, operationName = "修改文章评论")
	@ApiOperation(value = "修改文章评论", notes = "Comment")
	public JsonData<Void> updateByCommentSelective(@RequestBody @Valid Comment comment) {
		commentService.saveOrUpdate(comment);
		return JsonData.success();
	}

	@DeleteMapping
	@OptLog(operationType = CommonConst.DELETE, operationName = "删除评论")
	@ApiOperation(value = "删除", notes = "Comment")
	public JsonData<Void> deleteByIds(List<String> commentIds) {
		commentService.deleteCommentByIds(commentIds);
		return JsonData.success();
	}
	
}
