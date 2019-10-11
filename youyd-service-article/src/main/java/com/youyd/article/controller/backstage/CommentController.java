package com.youyd.article.controller.backstage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.annotation.OptLog;
import com.youyd.article.service.backstage.CommentService;
import com.youyd.constant.CommonConst;
import com.youyd.enums.StatusEnum;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.article.Comment;
import com.youyd.utils.JsonData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 文章的评论
 * @author: LGG
 * @create: 2019-01-11
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
	 * @param comment 实体
	 * @param queryVO 查询条件
	 * @return JsonData
	 */
	@GetMapping()
	public JsonData findCommentByCondition(Comment comment, QueryVO queryVO ){
		IPage<Comment> result = commentService.findCommentByCondition(comment,queryVO);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),result);
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return JsonData
	 */
	@GetMapping(value="/{id}")
	public JsonData findCommentByPrimaryKey(@PathVariable String id){
		Comment result = commentService.findCommentByPrimaryKey(id);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),result);
	}


	/**
	 * 增加
	 * @param comment 实体
	 * @return JsonData
	 */
	@PostMapping()
	@OptLog(operationType= CommonConst.ADD,operationName="增加文章评论")
	public JsonData insertComment(@RequestBody @Valid Comment comment){
		commentService.insertComment(comment);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 修改
	 * @param comment 实体
	 * @return JsonData
	 */
	@PutMapping
	@OptLog(operationType= CommonConst.MODIFY,operationName="修改文章评论")
	public JsonData updateByCommentSelective(@RequestBody @Valid Comment comment) {
		commentService.updateByCommentSelective(comment);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 删除
	 * @param commentIds 删除的id
	 * @return JsonData
	 */
	@DeleteMapping
	@OptLog(operationType= CommonConst.DELETE,operationName="添加文章评论")
	public JsonData deleteByIds(List<String> commentIds){
		commentService.deleteCommentByIds(commentIds);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}
	
}
