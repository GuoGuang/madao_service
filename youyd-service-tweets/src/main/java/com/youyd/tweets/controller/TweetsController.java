package com.youyd.tweets.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.pojo.Result;
import com.youyd.tweets.pojo.Tweets;
import com.youyd.tweets.service.TweetsService;
import com.youyd.utils.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 招聘控制层
 * @author: LGG
 * @create: 2018-09-26 15:59
 **/
@RestController
@RequestMapping("/tweets")
public class TweetsController {

	private final TweetsService tweetsService;

	@Autowired
	public TweetsController(TweetsService tweetsService) {
		this.tweetsService = tweetsService;
	}

	/**
	 * 按照条件查询招聘信息
	 * @return Result
	 */
	@GetMapping
	public Result findTweetsByCondition(Tweets tweets){
		IPage<Tweets> byCondition =  tweetsService.findTweetsByCondition(tweets);
		return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), byCondition);

	}

	/**
	 * 根据ID查询
	 * @param tweetsId 吐槽id
	 * @return Result
	 */
	@GetMapping(value="/{tweetsId}")
	public Result findTweetsByPrimaryKey(@PathVariable String tweetsId){
		Tweets result = tweetsService.findTweetsByPrimaryKey(tweetsId);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
	}

	/**
	 * 增加
	 * @param tweets : 吐槽实体
	 * @return Result
	 */
	@PostMapping()
	public Result insertTweets(@RequestBody Tweets tweets){
		tweetsService.insertTweets(tweets);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
	}

	/**
	 * 修改
	 * @param tweets
	 * @param id
	 * @return Result
	 */
	@PutMapping
	public Result updateByTweetsSelective(Tweets tweets){
		boolean updateResult = tweetsService.updateByTweetsSelective(tweets);
		return new Result(updateResult,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
	}

	/**
	 * 删除
	 * @param tweetsId:吐槽id数组
	 * @return Result
	 */
	@DeleteMapping
	public Result deleteByTweetsId(@RequestBody List tweetsId){
		boolean br = tweetsService.deleteByTweetsId(tweetsId);
		return new Result(br,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
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
		if (StringUtils.isBlank(page.toString()) || StringUtils.isBlank(size.toString())){
			return new Result(false,StatusCode.PARAM_ERROR.getCode(),StatusCode.PARAM_ERROR.getMsg());
		}
		Result result = tweetsService.findTweetsByParentid(parentId, page, size);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
	}

	/**
	 * 点赞
	 * @param id : 被点赞吐槽id
	 * @return
	 */
	@RequestMapping(value="/thumbUp/{id}",method=RequestMethod.PUT)
	public Result updateThumbUp(@PathVariable String id){
		tweetsService.updateThumbUp(id);
		return new Result(true,StatusCode.OK.getCode(),"点赞成功");
	}

}






