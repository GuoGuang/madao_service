package com.ibole.article.controller.backstage;

import com.ibole.annotation.OptLog;
import com.ibole.article.service.backstage.CommentService;
import com.ibole.config.CustomPageRequest;
import com.ibole.constant.CommonConst;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.article.Comment;
import com.ibole.utils.JsonData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 文章的评论
 **/

@Api(tags = "文章评论")
@RestController
@RequestMapping(value = "/comment",produces = "application/json")

public class CommentController {

	private final CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}


	/**
	 * 查询文章评论
	 *
	 * @param comment 实体
	 * @param queryVO 查询条件
	 * @return JsonData
	 */
	@GetMapping()
	public JsonData findCommentByCondition(Comment comment, QueryVO queryVO,
										   @RequestParam(name = "pageNum", defaultValue = "0") Integer pageNumber, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		queryVO.setPageable(new CustomPageRequest(pageNumber, pageSize));
		Page<Comment> result = commentService.findCommentByCondition(comment, queryVO);
		return JsonData.success(result);
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return JsonData
	 */
	@GetMapping(value="/{id}")
	public JsonData findCommentByPrimaryKey(@PathVariable String id) {
		Comment result = commentService.findCommentByPrimaryKey(id);
		return JsonData.success(result);
	}


	/**
	 * 增加
	 * @param comment 实体
	 * @return JsonData
	 */
	@PostMapping()
	@OptLog(operationType= CommonConst.ADD,operationName="增加文章评论")
	public JsonData insertComment(@RequestBody @Valid Comment comment) {
		commentService.saveOrUpdate(comment);
		return JsonData.success();
	}

	/**
	 * 修改
	 * @param comment 实体
	 * @return JsonData
	 */
	@PutMapping
	@OptLog(operationType= CommonConst.MODIFY,operationName="修改文章评论")
	public JsonData updateByCommentSelective(@RequestBody @Valid Comment comment) {
		commentService.saveOrUpdate(comment);
		return JsonData.success();
	}

	/**
	 * 删除
	 * @param commentIds 删除的id
	 * @return JsonData
	 */
	@DeleteMapping
	@OptLog(operationType= CommonConst.DELETE,operationName="添加文章评论")
	public JsonData deleteByIds(List<String> commentIds) {
		commentService.deleteCommentByIds(commentIds);
		return JsonData.success();
	}
	
}
