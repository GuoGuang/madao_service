package com.ibole.tweets.controller.backstage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ibole.annotation.OptLog;
import com.ibole.constant.CommonConst;
import com.ibole.enums.StatusEnum;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.Result;
import com.ibole.pojo.tweets.TweetsComment;
import com.ibole.tweets.service.TweetsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 吐槽评论
 **/
@RestController
@RequestMapping("/ts/tweetsComment")
public class StTweetsCommentController {

	private final TweetsCommentService tweetsCommentService;


	@Autowired
	public StTweetsCommentController(TweetsCommentService tweetsCommentService) {
		this.tweetsCommentService = tweetsCommentService;
	}

	/**
	 * 按照条件查询吐槽信息
	 * @return Result
	 */
	@GetMapping
	public Result findTweetsCommentByCondition(TweetsComment tweetsComment, QueryVO queryVO){
		IPage<TweetsComment> byCondition =  tweetsCommentService.findTweetsCommentByCondition(tweetsComment,queryVO);
		return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), byCondition);

	}

	/**
	 * 根据ID查询
	 * @param tweetsCommentId 吐槽id
	 * @return Result
	 */
	@GetMapping(value="/{tweetsCommentId}")
	public Result findTweetsCommentByPrimaryKey(@PathVariable String tweetsCommentId){
		TweetsComment result = tweetsCommentService.findTweetsCommentByPrimaryKey(tweetsCommentId);
		return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),result);
	}

	/**
	 * 增加
	 * @param tweetsComment : 吐槽实体
	 * @return Result
	 */
	@PostMapping()
	@OptLog(operationType= CommonConst.ADD,operationName="发布推特")
	public Result insertTweetsComment(@RequestBody @Valid TweetsComment tweetsComment, String tweetsId){
		tweetsCommentService.insertTweetsComment(tweetsComment,tweetsId);
		return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 修改
	 * @param tweetsComment
	 * @param id
	 * @return Result
	 */
	@PutMapping
	@OptLog(operationType= CommonConst.MODIFY,operationName="修改推特评论")
	public Result updateByTweetsCommentSelective(@Valid TweetsComment tweetsComment){
		boolean updateResult = tweetsCommentService.updateByTweetsCommentSelective(tweetsComment);
		return new Result(updateResult, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 删除
	 * @param tweetsCommentId:吐槽id数组
	 * @return Result
	 */
	@DeleteMapping
	@OptLog(operationType= CommonConst.DELETE,operationName="删除推特评论")
	public Result deleteByIds(@RequestBody List<String> tweetsCommentId){
		boolean br = tweetsCommentService.deleteByIds(tweetsCommentId);
		return new Result(br, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

}






