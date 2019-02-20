package com.youyd.article.controller.backstage;

import com.youyd.article.pojo.Category;
import com.youyd.article.pojo.Comment;
import com.youyd.article.service.CommentService;
import com.youyd.pojo.Result;
import com.youyd.utils.StatusCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 文章的评论
 * @author: LGG
 * @create: 2019-01-11
 **/

@Api(tags = "文章评论")
@RestController
@RequestMapping(value = "/sa/comment",produces = "application/json")

public class CommentController {

	@Autowired
	private CommentService commentService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@GetMapping()
	public Result findCommentByCondition(){
		List result = commentService.findCommentByCondition();
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@GetMapping(value="/{id}")
	public Result findCommentByPrimaryKey(@PathVariable String id){
		Comment result = commentService.findCommentByPrimaryKey(id);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
	}


	/**
	 * 增加
	 * @param comment
	 */
	@PostMapping()
	public Result insertComment(@RequestBody Comment comment){
		commentService.insertComment(comment);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
	}

	/**
	 * 修改
	 */
	@PutMapping
	public Result updateByCommentSelective(@RequestBody Comment comment) {
		commentService.updateByCommentSelective(comment);
		return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

	/**
	 * 删除
	 * @param commentIds
	 */
	@DeleteMapping
	public Result deleteByIds(List<Long> commentIds){
		commentService.deleteByIds(commentIds);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
	}
	
}
