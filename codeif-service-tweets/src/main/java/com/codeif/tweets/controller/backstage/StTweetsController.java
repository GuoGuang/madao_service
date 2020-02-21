package com.codeif.tweets.controller.backstage;

import com.codeif.annotation.OptLog;
import com.codeif.constant.CommonConst;
import com.codeif.enums.StatusEnum;
import com.codeif.pojo.QueryVO;
import com.codeif.pojo.Result;
import com.codeif.pojo.tweets.Tweets;
import com.codeif.tweets.service.TweetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 吐槽控制层
 **/
@RestController
@RequestMapping("/ts/tweet")
public class StTweetsController {

	private final TweetsService tweetsService;

	@Autowired
	public StTweetsController(TweetsService tweetsService) {
		this.tweetsService = tweetsService;
	}

	/**
	 * 按照条件查询吐槽信息
	 * @return Result
	 */
	@GetMapping
	public Result findTweetsByCondition(Tweets tweets, QueryVO queryVO){
//		IPage<Tweets> byCondition =  tweetsService.findTweetsByCondition(tweets,queryVO);
//		return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), byCondition);
        return null;

    }

	/**
	 * 根据ID查询
	 * @param tweetsId 吐槽id
	 * @return Result
	 */
	@GetMapping(value="/{tweetsId}")
	public Result findTweetsByPrimaryKey(@PathVariable String tweetsId){
		Tweets result = tweetsService.findTweetsByPrimaryKey(tweetsId);
		return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),result);
	}

	/**
	 * 增加
	 * @param tweets : 吐槽实体
	 * @return Result
	 */
	@PostMapping()
	@OptLog(operationType= CommonConst.ADD,operationName="发布推特")
	public Result insertTweets(@RequestBody @Valid Tweets tweets){
		tweetsService.insertTweets(tweets);
		return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 修改
	 * @param tweets
	 * @param id
	 * @return Result
	 */
	@PutMapping
	@OptLog(operationType= CommonConst.MODIFY,operationName="修改推特")
	public Result updateByTweetsSelective(@Valid Tweets tweets){
		boolean updateResult = tweetsService.updateByTweetsSelective(tweets);
		return new Result(updateResult, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 删除
	 * @param tweetsId:吐槽id数组
	 * @return Result
	 */
	@DeleteMapping
	@OptLog(operationType= CommonConst.DELETE,operationName="删除推特")
	public Result deleteByTweetsId(@RequestBody List tweetsId){
		boolean br = tweetsService.deleteByTweetsId(tweetsId);
		return new Result(br, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 根据上级ID查询吐槽分页数据
	 * @param parentId
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(value="/comment/{parentId}/{page}/{size}")
	public Result findByParentid(@PathVariable String parentId, @PathVariable Integer page,@PathVariable Integer size){
		Result result = tweetsService.findTweetsByParentid(parentId);
		return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),result);
	}

	/**
	 * 点赞
	 * @param id : 被点赞吐槽id
	 * @return
	 */
/*	@RequestMapping(value="/thumbUp/{id}",method=RequestMethod.PUT)
	public Result updateThumbUp(@PathVariable String id){
		tweetsService.updateThumbUp(id);
		return new Result(true,StatusCode.OK.getCode(),"点赞成功");
	}*/

}






