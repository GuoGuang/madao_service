package com.ibole.tweets.controller.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ibole.enums.StatusEnum;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.Result;
import com.ibole.pojo.tweets.TweetsComment;
import com.ibole.tweets.service.TweetsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 吐槽评论
 **/
@RestController
@RequestMapping("/tweetsComment")
public class TweetsCommentController {

	private final TweetsCommentService tweetsCommentService;


	@Autowired
	public TweetsCommentController(TweetsCommentService tweetsCommentService) {
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
	public Result insertTweetsComment(@RequestBody TweetsComment tweetsComment,String tweetsId){
		tweetsCommentService.insertTweetsComment(tweetsComment,tweetsId);
		return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 修改
	 * @param tweetsComment
	 * @return Result
	 */
	@PutMapping
	public Result updateByTweetsCommentSelective(TweetsComment tweetsComment){
		boolean updateResult = tweetsCommentService.updateByTweetsCommentSelective(tweetsComment);
		return new Result(updateResult, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 删除
	 * @param tweetsCommentId:吐槽id数组
	 * @return Result
	 */
	@DeleteMapping
	public Result deleteByIds(@RequestBody List<String> tweetsCommentId){
		boolean br = tweetsCommentService.deleteByIds(tweetsCommentId);
		return new Result(br, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

}






