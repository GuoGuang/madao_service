package com.youyd.article.controller.backstage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.article.service.backstage.SaCommentService;
import com.youyd.enums.StatusEnum;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.article.Comment;
import com.youyd.utils.JsonData;
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

public class SaCommentController {

	private final SaCommentService saCommentService;

	@Autowired
	public SaCommentController(SaCommentService saCommentService) {
		this.saCommentService = saCommentService;
	}


	/**
	 * 查询文章评论
	 * @param comment 实体
	 * @param queryVO 查询条件
	 * @return JsonData
	 */
	@GetMapping()
	public JsonData findCommentByCondition(Comment comment, QueryVO queryVO ){
		IPage<Comment> result = saCommentService.findCommentByCondition(comment,queryVO);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),result);
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return JsonData
	 */
	@GetMapping(value="/{id}")
	public JsonData findCommentByPrimaryKey(@PathVariable String id){
		Comment result = saCommentService.findCommentByPrimaryKey(id);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),result);
	}


	/**
	 * 增加
	 * @param comment 实体
	 * @return JsonData
	 */
	@PostMapping()
	public JsonData insertComment(@RequestBody Comment comment){
		saCommentService.insertComment(comment);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 修改
	 * @param comment 实体
	 * @return JsonData
	 */
	@PutMapping
	public JsonData updateByCommentSelective(@RequestBody Comment comment) {
		saCommentService.updateByCommentSelective(comment);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 删除
	 * @param commentIds 删除的id
	 * @return JsonData
	 */
	@DeleteMapping
	public JsonData deleteByIds(List<String> commentIds){
		saCommentService.deleteCommentByIds(commentIds);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}
	
}
